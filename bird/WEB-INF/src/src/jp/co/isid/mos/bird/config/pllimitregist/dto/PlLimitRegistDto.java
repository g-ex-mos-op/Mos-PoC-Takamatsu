/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.dto;

import java.util.List;

/**
 * P/L入力上下限設定DTO
 * 
 * @author xyuchida
 */
public class PlLimitRegistDto {

    /**
     * P/Lタイプ
     */
    private String plType;

    /**
     * P/L上下限リスト
     */
    private List plLimitList;

    public String getPlType() {
        return plType;
    }

    public void setPlType(String plType) {
        this.plType = plType;
    }

    public List getPlLimitList() {
        return plLimitList;
    }

    public void setPlLimitList(List plLimitList) {
        this.plLimitList = plLimitList;
    }

    public boolean isPlLimitListEmpty() {
        return getPlLimitList() == null || getPlLimitList().isEmpty();
    }
}
