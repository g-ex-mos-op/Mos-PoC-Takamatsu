package jp.co.isid.mos.bird.commonform.svsearchnew.dto;

import java.util.List;

/**
 * 検索結果DTO
 * @author xnkusama
 */
public class SvSearchResultDto {

    /* 選択SVコード（会社コード＋SVコード） */
    private String selectSvInfo;
    /* SVリスト */
    private List listSv;

    public List getListSv() {
        return listSv;
    }

    public void setListSv(List listSv) {
        this.listSv = listSv;
    }

    public int getSvSearchListSize() {
        return (getListSv() == null) ? 0 : getListSv().size();
    }

    public String getSelectSvInfo() {
        return selectSvInfo;
    }

    public void setSelectSvInfo(String selectSv) {
        this.selectSvInfo = selectSv;
    }
}