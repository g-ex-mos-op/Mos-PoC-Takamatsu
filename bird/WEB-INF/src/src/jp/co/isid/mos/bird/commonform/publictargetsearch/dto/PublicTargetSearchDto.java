/*
 * 作成日: 2006/01/16
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.dto;


import java.util.ArrayList;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;

/**
 * 公開対象選択用のDTO
 * @author xytamura
 */
public class PublicTargetSearchDto extends PublicTargetDto {


    /**
     * 呼び出し元のビューID
     */
    private String navigationCase;

    /**
     * アクションフラグ true:決定 false:戻る
     */
    private boolean actionFlg;

    /**
     * 参照フラグ true:参照のみ false:選択可
     */
    private boolean referenceFlg;

    /**
     * 検索初期処理起動フラグ
     */  
    private boolean initFlag = true;

    
    /**
     * アクションフラグを取得します。
     * @return アクションフラグ
     */
    public boolean isActionFlg() {
        return actionFlg;
    }

    /**
     * アクションフラグを設定します。
     * @param actionFlg アクションフラグ
     */
    public void setActionFlg(boolean actionFlg) {
        this.actionFlg = actionFlg;
    }

    /**
     * 呼び出し元のビューIDを取得します。
     * @return navigationCase 呼び出し元のビューID
     */
    public String getNavigationCase() {
        return navigationCase;
    }

    /**
     * 呼び出し元のビューIDを設定します。
     * @param navigationCase 呼び出し元のビューID
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    /**
     * 参照フラグを設定します。
     * @param referenceFlg
     */
    public void setReferenceFlg(boolean referenceFlg) {
        this.referenceFlg = referenceFlg;
    }

    /**
     * 参照フラグを取得します。
     * @return
     */
    public boolean isReferenceFlg() {
        return referenceFlg;
    }

    /**
     * 公開対象情報をクリアします。
     */
    public void clearData() {
        if (getListTrnControlShozoku() != null) {
            setListTrnControlShozoku(new ArrayList());
        }
        if (getListTrnControlCompany() != null) {
            setListTrnControlCompany(new ArrayList());
        }
        if (getListTrnControlGyotai() != null) {
            setListTrnControlGyotai(new ArrayList());
        }
        if (getListTrnControlGyotaiKobetu() != null) {
            setListTrnControlGyotaiKobetu(new ArrayList());
        }
        if (getListTrnControlGyotaiTenpo() != null) {
            setListTrnControlGyotaiTenpo(new ArrayList());
        }
    }


    /**
     * 所属による絞りこみ情報の件数を取得します。
     * @return 件数
     */
    public int getSizeTrnControlShozoku() {
        if (getListTrnControlShozoku() == null) {
            return 0;
        }
        return getListTrnControlShozoku().size();

    }

    /**
     * 会社による絞りこみ情報の件数を取得します。
     * @return 件数
     */
    public int getSizeTrnControlCompany() {
        if (getListTrnControlCompany() == null) {
            return 0;
        }
        return getListTrnControlCompany().size();
    }

    /**
     * 業態による絞りこみ情報の件数を取得します。
     * @return 件数
     */
    public int getSizeTrnControlGyotai() {
        if (getListTrnControlGyotai() == null) {
            return 0;
        }
        return getListTrnControlGyotai().size();

    }

    /**
     * 業態個別による絞りこみ情報の件数を取得します。
     * @return 件数
     */
    public int getSizeTrnControlGyotaiKobetu() {
        if (getListTrnControlGyotaiKobetu() == null) {
            return 0;
        }
        return getListTrnControlGyotaiKobetu().size();

    }

    /**
     * 個店による絞りこみ情報の件数を取得します。
     * @return 件数
     */
    public int getSizeTrnControlGyotaiTenpo() {
        if (getListTrnControlGyotaiTenpo() == null) {
            return 0;
        }
        return getListTrnControlGyotaiTenpo().size();

    }
    
    /**
     *  検索初期処理起動フラグを取得します。
     * @return  検索初期処理起動フラグ
     */
    public boolean isInitFlag() {
        return initFlag;
    }
    /**
     *  検索初期処理起動フラグを設定します。
     * @param initFlag  検索初期処理起動フラグ
     */
    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }
}