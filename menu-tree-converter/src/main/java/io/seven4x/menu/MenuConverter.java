package io.seven4x.menu;

import java.util.*;

public class MenuConverter {



    /**
     * 将List数据结构转换为树结构
     * Convert java arrayList of Parent/child relation into tree
     *
     * @param menuInfos
     * @return
     */
    public List<MenuInfo> structureMenu(List<MenuInfo> menuInfos) {
        Map<String, MenuInfo> map = new HashMap(menuInfos.size());
        for (MenuInfo info : menuInfos) {
            map.put(info.getId(), info);
        }
        Map<String, MenuInfo> hm = new LinkedHashMap();

        for (MenuInfo p : menuInfos) {

            //  ----- Child -----
            MenuInfo mmdChild = null;
            if (hm.containsKey(p.getId())) {
                mmdChild = hm.get(p.getId());
            } else {
                mmdChild = p;//
                hm.put(p.getId(), p);
            }

            // no need to set ChildrenItems list because the constructor created a new empty list


            // ------ Parent ----
            MenuInfo mmdParent;
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
        List<MenuInfo> DX = new ArrayList<MenuInfo>();
        for (MenuInfo mmd : hm.values()) {
            if (mmd != null && mmd.getParentId().equals(MenuInfo.getRootID())){
                DX.add(mmd);
            }
        }
        return DX;
    }
}
