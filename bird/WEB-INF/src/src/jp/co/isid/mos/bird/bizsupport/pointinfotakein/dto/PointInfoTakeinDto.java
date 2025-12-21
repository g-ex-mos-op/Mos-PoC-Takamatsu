/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto;

import java.util.List;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * ポイント情報取込Dto
 * @author yushuncheng
 *
 */
public class PointInfoTakeinDto implements CsvOutputDto {


    /* 検索条件 */
    /**
     * 処理選択 1:基本ポイント付与 2:海外赴任精算 3:退職 4:役員情報登録
     */
    private int processMode;

    /**
     * 条件：株式報酬制度コード
     */
    private String conSeidoCd;

    /**
     * 取込処理完了フラグ
     */
    private boolean uploadFinish = false;

    /**
     * 業績ポイント作成完了フラグ
     */
    private boolean keisanFinish = false;

	/**
     * 共通
     * BirdUserInfo
     */
    private BirdUserInfo birdUserInfo;

	/**
	 * 株式報酬制度プルダウンリスト
	 */
	private List<UISeidoMst> listSeido;

	/**
	 * アップロードファイル
	 */
	private UploadedFile uploadedFile;

	/**
	 * CSVエラーファイル名
	 */
	private String csvErrorName;

	/* CSVアップロード */
    /**
     * エラーフラグ
     */
    private boolean uploadError;
    /**
     * 登録内容リスト
     */
    private List listUploadData;
    /**
     * エラーデータ
     */
    private List listUploadErrorData;


    /**
     * 画面表示用フラグの初期化
     */
	public void clearInit() {
		// アップロード完了設定：未完了
        setUploadFinish(false);
        // 業績ポイント作成完了設定：未完了
        setKeisanFinish(false);
        setUploadError(false);
        setCsvErrorName("");
	}


	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public int getProcessMode() {
		return processMode;
	}

	public void setProcessMode(int processMode) {
		this.processMode = processMode;
	}

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

	public String getConSeidoCd() {
		return conSeidoCd;
	}

	public void setConSeidoCd(String conSeidoCd) {
		this.conSeidoCd = conSeidoCd;
	}

    public List<UISeidoMst> getListSeido() {
        return listSeido;
    }

    public void setListSeido(List<UISeidoMst> listSeido) {
        this.listSeido = listSeido;
    }

    public boolean isEmptyListTarget() {
        return getListSeido() == null || getListSeido().isEmpty();
    }

    public boolean isUploadError() {
        return uploadError;
   }

    public void setUploadError(boolean uploadError) {
        this.uploadError = uploadError;
    }

    public List getListUploadData() {
        return listUploadData;
    }

    public void setListUploadData(List listUploadData) {
        this.listUploadData = listUploadData;
    }

    public List getListUploadErrorData() {
        return listUploadErrorData;
    }

    public void setListUploadErrorData(List listUploadErrorData) {
        this.listUploadErrorData = listUploadErrorData;
    }

    public boolean isUploadFinish() {
		return uploadFinish;
	}

	public void setUploadFinish(boolean uploadFinish) {
		this.uploadFinish = uploadFinish;
	}

	public boolean isKeisanFinish() {
		return keisanFinish;
	}

	public void setKeisanFinish(boolean keisanFinish) {
		this.keisanFinish = keisanFinish;
	}

	public String getCsvErrorName() {
		return csvErrorName;
	}

	public void setCsvErrorName(String csvName) {
		this.csvErrorName = csvName;
	}

}
