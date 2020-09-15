package io.seven4x.menu;

public interface MenuInfo {
    /**
     * 获取根节点
     * 
     * @return
     */
    static String getRootID() {
        return "0";
    }

    /**
     * 父节点添加保存子节点
     * 
     * @param mmdChild
     */
     void addSubMenu(MenuInfo mmdChild);

    /**
     * 菜单父节点
     * 
     * @return
     */
    String getParentId();

    /**
     * 菜单id
     * 
     * @return
     */
    String getId();
}
