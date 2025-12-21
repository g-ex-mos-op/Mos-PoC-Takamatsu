/*
 * 作成日: 2006/02/11
 * 更新日: 2007/08/29 指定条件の汎用画面別ロール制御データを全件取得する機能を追加
 */
package jp.co.isid.mos.bird.framework.logic;


import java.util.List;

import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;

/**
 * ユーザの汎用画面ロール制御を取得します
 * @author xnlee
 */
public interface GetGamenRoleLogic {
    /* 画面ロール */
    public List excute(final GamenRoleDto dto);
    
    /**
     *  汎用画面別ロール制御 データ取得
     *  　画面ID、分類コード、ユーザーIDを指定し、
     *  　該当する制御情報をListで返す
     *  @param GamenRoleDto dto 汎用画面別ロール制御DTO
     *  @return List 指定パラメータで検索した結果（CtlGamenRole）
     */
    public List getGamenRole(final GamenRoleDto dto);
}