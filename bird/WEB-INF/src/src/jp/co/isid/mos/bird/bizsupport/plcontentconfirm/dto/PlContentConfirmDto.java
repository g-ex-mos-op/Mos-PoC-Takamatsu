/*
 * 作成日: 2006/3/27
 */
package jp.co.isid.mos.bird.bizsupport.plcontentconfirm.dto;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;



/**
 * P/L内容確認用Dto<br>
 * @author itamoto
 */
public class PlContentConfirmDto implements PdfOutputDto {
    
	/* PDFファイル名 */
	private String fileName;

	/* P/Lデータ情報 */
    private TrnPLInfo trnPLInfo;
    /* 前月PLデータ情報 */
    private TrnZenPLInfo trnZenPLInfo;

    /* 売上高比率 */
    private BigDecimal uriagedakaRatio;
    /* 売上原価比率 */
    private BigDecimal uriagegenkaRatio;
    /* 売上総利益比率 */
    private BigDecimal uriageSoRiekiRatio;

    
	/**
	 * PDFファイル名の設定
	 * @return fileName を戻します。
	 */
    public String getFileName() {
    	if (this.fileName == null) {
// change start 2006/08/16 xkhata ファイル名称修正 
//    		fileName = "PDFSample.pdf";
            fileName = "PL" + getTrnPLInfo().getOnerCd() + "_" + getTrnPLInfo().getPlYm() + ".pdf";
// end
        }
    	return fileName;
	}
	/**
	 * PDFファイル名の設定
	 * @param fileName fileName を設定。
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * P/Lデータ情報の設定
	 * @return trnPLInfo を戻します。
	 */
	public TrnPLInfo getTrnPLInfo() {
		return trnPLInfo;
	}
	/**
	 * P/Lデータ情報の設定
	 * @param trnPLInfo trnPLInfo を設定。
	 */
	public void setTrnPLInfo(TrnPLInfo trnPLInfo) {
		this.trnPLInfo = trnPLInfo;
	}

	/**
	 * 前月PLデータ情報の設定
	 * @return trnZenPLInfo を戻します。
	 */
	public TrnZenPLInfo getTrnZenPLInfo() {
		return trnZenPLInfo;
	}
	/**
	 * 前月PLデータ情報の設定
	 * @param trnZenPLInfo trnZenPLInfo を設定。
	 */
	public void setTrnZenPLInfo(TrnZenPLInfo trnZenPLInfo) {
		this.trnZenPLInfo = trnZenPLInfo;
	}

	/**
	 * 売上高比率の設定
	 * @return uriagedakaRatio を戻します。
	 */
	public BigDecimal getUriagedakaRatio() {
		return uriagedakaRatio;
	}
	/**
	 * 売上高比率の設定
	 * @param uriagedakaRatio uriagedakaRatio を設定。
	 */
	public void setUriagedakaRatio(BigDecimal uriagedakaRatio) {
		this.uriagedakaRatio = uriagedakaRatio;
	}

	/**
	 * 売上原価比率の設定
	 * @return uriagegenkaRatio を戻します。
	 */
	public BigDecimal getUriagegenkaRatio() {
		return uriagegenkaRatio;
	}
	/**
	 * 売上原価比率の設定
	 * @param uriagegenkaRatio uriagegenkaRatio を設定。
	 */
	public void setUriagegenkaRatio(BigDecimal uriagegenkaRatio) {
		this.uriagegenkaRatio = uriagegenkaRatio;
	}

	/**
	 * 売上総利益比率の設定
	 * @return uriageSoRiekiRatio を戻します。
	 */
	public BigDecimal getUriageSoRiekiRatio() {
		return uriageSoRiekiRatio;
	}
	/**
	 * 売上総利益比率の設定
	 * @param uriageSoRiekiRatio uriageSoRiekiRatio を設定。
	 */
	public void setUriageSoRiekiRatio(BigDecimal uriageSoRiekiRatio) {
		this.uriageSoRiekiRatio = uriageSoRiekiRatio;
	}
}
