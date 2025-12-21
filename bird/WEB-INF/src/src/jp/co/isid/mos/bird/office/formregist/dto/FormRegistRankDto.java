package jp.co.isid.mos.bird.office.formregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * フォーム登録条件画面DTO
 * @author xytamura
 */
public class FormRegistRankDto implements DownloadDto {

    /* フォーム一覧 */
    private List listForm;
    /* ダウンロードアクションを実行したリスト内のIndex */
    private int downloadIndex;

    

    /**
     * フォーム一覧の件数を取得します。
     * @return int 件数
     */
    public int getListFormSize() {
        int size = 0;
        if (getListForm() != null) {
            size = getListForm().size();
        }
        return size;
    }

    /**
     * フォーム一覧設定処理
     * @param listForm listForm を設定。
     */
    public void setListForm(List listForm) {
        this.listForm = listForm;
    }

    /**
     * フォーム一覧取得処理
     * @return listForm を戻します。
     */
    public List getListForm() {
        return listForm;
    }

    /**
     * @param downloadIndex downloadIndex を設定。
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }

    /**
     * @return downloadIndex を戻します。
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }

}
