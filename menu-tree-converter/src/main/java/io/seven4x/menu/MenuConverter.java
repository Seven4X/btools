package io.seven4x.menu;

import java.util.*;

public class MenuConverter {



    /**
     * 将List数据结构转换为树结构
     * Convert java arrayList of Parent/child relation into tree
     * 利用范型避免返回参数后调用者再进行强转操作
     * @param menuInfos
     * @return
     */
    public static  <T extends MenuInfo> List<T> structureMenu(List<T> menuInfos) {
        Map<String, T> map = new HashMap<>(menuInfos.size());
        for (T info : menuInfos) {
            map.put(info.getId(), info);
        }
        Map<String, T> hm = new LinkedHashMap<>();

        for (T p : menuInfos) {

            //  ----- Child -----
            T mmdChild = null;
            if (hm.containsKey(p.getId())) {
                mmdChild = hm.get(p.getId());
            } else {
                mmdChild = p;//
                hm.put(p.getId(), p);
            }

            // no need to set ChildrenItems list because the constructor created a new empty list


            // ------ Parent ----
            T mmdParent;
            if (hm.containsKey(p.getParentId())) {
                mmdParent = hm.get(p.getParentId());
            } else {
                mmdParent = map.get(p.getParentId());
                if(mmdParent!=null){
                    hm.put(p.getParentId(), mmdParent);
                }
            }
            //根节点是没有父节点的
            if (mmdParent != null) {
                mmdParent.addSubMenu(mmdChild);
            }


        }
        // Get the root
        List<T> DX = new ArrayList<T>();
        for (T mmd : hm.values()) {
            if (mmd != null && mmd.getParentId().equals(MenuInfo.getRootID())){
                DX.add(mmd);
            }
        }
        return DX;
    }
}
