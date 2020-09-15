package io.seven4x.menu;

import org.junit.jupiter.api.Test;

import lombok.Data;
import lombok.NoArgsConstructor;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class MenuConverterTest {

    @Data
    @NoArgsConstructor
    class TestMenu implements MenuInfo {
        public TestMenu(final String id, final String parentId) {
            this.id = id;
            this.parentId = parentId;
        }

        String id;
        String parentId;
        List<TestMenu> subs;

        @Override
        public void addSubMenu(MenuInfo mmdChild) {
            if (subs == null) {
                subs = new ArrayList<>();
            }
            subs.add((TestMenu)mmdChild);

        }

        @Override
        public String toString() {
            return this.id + "=>" + this.parentId;
        }

        
    }

    /**
     * 正常的数据
     */
    @Test
    void structureMenuNormalDate() {
        final List<TestMenu> menuInfos = new ArrayList<>();
        menuInfos.add(new TestMenu("L0-0", MenuInfo.getRootID()));
        menuInfos.add(new TestMenu("L1-1", "L0-0"));
        menuInfos.add(new TestMenu("L1-2", "L0-0"));
        menuInfos.add(new TestMenu("L2-1", "L1-1"));
        menuInfos.add(new TestMenu("L3-1", "L2-1"));

        List<TestMenu> result =  MenuConverter.structureMenu(menuInfos);

        assertThat(result).isNotEmpty();
        //L0-0 => L1-1 => L2-1
        assertThat(result.get(0).getSubs().get(0).getSubs().get(0).getId()).isEqualTo("L2-1");

    }


    /**
     * 存在脏数据 循环饮用的情况时
     * L1-2 => L3-1
     * L3-1 => L4-1
     * L4-1 => L1-2
     * 存在脏数据时仍然能够正常构建树结构，迭代输出的时候会死循环
     * 这里可以考虑增加一个快关是否检测脏数据
     */
    @Test
    void structureMenuDirtyDate() {
        final List<TestMenu> menuInfos = new ArrayList<>();
        menuInfos.add(new TestMenu("L0-0", MenuInfo.getRootID()));
        menuInfos.add(new TestMenu("L1-1", "L0-0"));
        //dirty date
        menuInfos.add(new TestMenu("L1-2", "L0-0"));
        menuInfos.add(new TestMenu("L1-2", "L3-1"));
        menuInfos.add(new TestMenu("L2-1", "L1-1"));
        menuInfos.add(new TestMenu("L3-1", "L4-1"));
        menuInfos.add(new TestMenu("L4-1", "L1-2"));

        List<TestMenu> result =  MenuConverter.structureMenu(menuInfos);

        assertThat(result).isNotEmpty();
        //L0-0 => L1-1 => L2-1
        assertThat(result.get(0).getSubs().get(0).getSubs().get(0).getId()).isEqualTo("L2-1");

    }
}