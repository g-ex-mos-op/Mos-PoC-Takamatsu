package jp.co.isid.mos.bird.inforegist.documentregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 文書登録条件画面DTO
 * @author xnkusama
 */
public class DocumentRegistRegistRankDto implements DownloadDto {

    /* 文書一覧 */
    private List listBunsho;
    /* ダウンロードアクションを実行したリスト内のIndex */
    private int downloadIndex;

    

    /**
     * 文書一覧の件数を取得します。
     * @return int 件数
     */
    public int getListBunshoSize() {
        int size = 0;
        if (getListBunsho() != null) {
            size = getListBunsho().size();
        }
        return size;
    }

    /**
     * 文書一覧設定処理
     * @param _listBunsho _listBunsho を設定。
     */
    public void setListBunsho(List listBunsho) {
        this.listBunsho = listBunsho;
    }

    /**
     * 文書一覧取得処理
     * @return _listBunsho を戻します。
     */
    public List getListBunsho() {
        return listBunsho;
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
