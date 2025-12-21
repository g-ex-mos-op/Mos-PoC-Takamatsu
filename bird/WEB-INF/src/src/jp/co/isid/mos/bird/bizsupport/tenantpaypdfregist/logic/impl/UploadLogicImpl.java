/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.impl;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.MstCategoryShubetuInfo;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.IllegalOperationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;
import jp.co.isid.mos.bird.framework.util.FileUtil;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;

/**
 * アップロードロジック
 * 
 * 作成日:2009/06/24
 * @author xkinu
 *
 */
public class UploadLogicImpl implements UploadLogic {
    /** ロジックID */
    public static final String LOGIC_ID = TenantPayPdfRegistUtil.LOGIC_ID_UPLOAD;
	/** PDF 拡張子ドット付「.PDF」*/
	private static final String EXTENSION = ".PDF";

    /** 
     * ファイル名取得処理
     * 
	 * ファイル名の頭に、「店コード+対象年月+回数+’_’」を付加する。
	 * 例）meisai.pdf ⇒ 0200120090601_meisai.pdf
     */
    public String getFileName(UploadDto uploadDto) {
    	SessionDto sessionDto = (SessionDto)uploadDto;
    	TrnPayDetaileStatement eRegist = sessionDto.getRegistDataEntity();
        return eRegist.getUrikakeCd()+eRegist.getKeisanYm()+eRegist.getSeq()+"_"+eRegist.getFileName();
    }

    /**
     * ファイル フルパス取得処理
     * @param UploadDto
     * @return String
     */
    public String getFileFullPath(UploadDto uploadDto) {
    	SessionDto sessionDto = (SessionDto)uploadDto;
    	MstCategoryShubetuInfo categoryInfo = sessionDto.getCategoryShubetuInfo();
    	String file = categoryInfo.getSaveDir().trim();
        file += getFileName(uploadDto);
        
        return file;
    }

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileName(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileName(UploadDto uploadDto) {     
        return uploadDto.getTempFileName();
    }

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileFullPath(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileFullPath(UploadDto uploadDto) {
    	SessionDto sessionDto = (SessionDto)uploadDto;
    	MstCategoryShubetuInfo categoryInfo = sessionDto.getCategoryShubetuInfo();
    	String file = categoryInfo.getSaveDir().trim();
        file += getTempFileName(uploadDto);
        return file;
    }

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#validate()
     */
    public void validate(final UploadDto uploadDto) throws ApplicationException {
        // ファイル必須チェック
        if (uploadDto.getUploadedFile() == null) {
            throw new NotNullException("ファイル", "uploadFile", 0);
        }
        String file = uploadDto.getUploadedFile().getName();
        String filename = file.substring(file.lastIndexOf(FileUtil.getFileSeparator()) + 1);
        // PDF拡張子ファイルかのチェック
        if (!filename.toUpperCase().endsWith(EXTENSION)) {
            throw new IllegalOperationException("PDFファイル以外", "登録","uploadFile", 0);
        }
        // ファイル名称のサイズチェック
        LengthVerifier lent = new LengthVerifier( TenantPayPdfRegistUtil.FILE_NAME_MAX_LENGTH );
        if (!lent.validate(filename)) {
            throw new GenericCommentException("ファイル名は全角３０文字まで","uploadFile", 0);
        }
        
        // 禁則文字チェック
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if (! defJap.validate(filename)) {
            throw new InvalidInputException("ファイル名","uploadFile", 0);
        }
        
        // ファイル存在チェック（空のファイルは不可）
        if (uploadDto.getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル","uploadFile", 0);
        }

    }

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getPageKeyTemporary()
     */
    public String getPageKeyTemporary(final UploadDto uploadDto) {
        return null;
    }

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getPageKey()
     */
    public String getPageKey(final UploadDto uploadDto) {
        return null;
    }
}
