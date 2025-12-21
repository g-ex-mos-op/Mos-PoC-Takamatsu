package jp.co.isid.mos.bird.framework.action.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;
import jp.co.isid.mos.bird.framework.util.FileUtil;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * アップロード アクションクラス
 * @author xnkusama
 */
public class FileUploadActionImpl implements FileUploadAction {
    public static final String existsFileOnServer_ACTION_ID = "BFW006A01";
    public static final String upload_ACTION_ID = "BFW006A02";
    public static final String uploadTemporary_ACTION_ID = "BFW006A03";
    //テンポラリファイル名 フォーマットパターン
    private static final String TMP_FILE_FORMAT_PTN = "000000000000000";
    // ファイルセパレーター
//---2006/03/03 ファイル区切り文字を共通クラスから取得するように変更（実質Windows専用）
//    private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    private String FILE_SEPARATOR = FileUtil.getFileSeparator();
    // エラーメッセージ１
    private static final String ERR_MSG_UPLOAD_ERR = "アップロード";
    // エラーメッセージ詳細（テンポラリ作成処理中エラー）
    private static final String ERR_MSG_DETAIL_TEMP_CREATE = "テンポラリファイル作成処理中にエラーが起こりました。";
    

    private UploadLogic _uploadLogic;
    private UploadDto _uploadDto;
    
    public void setUploadLogic(final UploadLogic uploadLogic) {
        this._uploadLogic = uploadLogic;
    }
    private UploadLogic getUploadLogic() {
        return _uploadLogic;
    }
    
    public void setUploadDto(final UploadDto uploadDto) {
        this._uploadDto = uploadDto;
    }
    private UploadDto getUploadDto() {
        return _uploadDto;
    }
    
    /**
     * ファイル存在確認メソッド
     * @return
     */
    public boolean existsFileOnServer() {
        String path = getUploadLogic().getFileFullPath(getUploadDto());
        
        File file = new File(path);
        
        return file.exists();
    }
    
    // テンポラリファイル保存
    private void createTempFile() throws IOException {
        String filename = getUploadLogic().getTempFileFullPath(getUploadDto());
        //
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //
            InputStream inp = getUploadDto().getUploadedFile().getInputStream();
            // 保存File作成
            File file = new File(filename);
            bis = new BufferedInputStream(inp);
            bos = new BufferedOutputStream(new FileOutputStream(file));
            
            byte buf[] = new byte[256];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        }
        finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }
    
    /**
     * ファイル書込み処理
     * @throws IOException
     */
    private void writeFile() throws IOException {
        //アップロードするファイルと同名のファイルを削除
        File oldFile = new File(getUploadLogic().getFileFullPath(getUploadDto()));
        oldFile.delete();
        
        //ファイル書込み（テンポラリをリネームする）
        File newFile = new File(getUploadLogic().getTempFileFullPath(getUploadDto()));
        newFile.renameTo(oldFile);
    }
    
    /*
     * テンポラリファイル名作成
     * タイムスタンプより作成
     */
    private void createTempFileName() {
        /* システムタイムスタンプを15桁の数値にし、ファイル名とする */
        CodeFormatter formatter = new CodeFormatter(15);
        formatter.setFormatPattern(TMP_FILE_FORMAT_PTN);
        String filename = "TMP" + formatter.format(String.valueOf(System.currentTimeMillis()), true);
        getUploadDto().setTempFileName(filename);
    }
    
    /**
     * テンポラリファイル作成
     * @return String
     * @throws IOException
     */
    public String uploadTemporary() throws ApplicationException {
        try {
            // 事前チェック処理
            getUploadLogic().validate(getUploadDto());
            // テンポラリファイル名、ファイル作成
            createTempFileName();
            createTempFile();
            
//            // ファイル情報をDTOへ設定
//            String filename = getUploadDto().getUploadFileName();
//            if (filename == null || "".equals(filename)) {
//                // ファイル名が指定されていない場合は、ファイルオブジェクトより取得する
//                String file = getUploadDto().getUploadedFile().getName();
//                filename = file.substring(file.lastIndexOf(FILE_SEPARATOR) + 1);
//            }
            // ファイル名が指定されていない場合は、ファイルオブジェクトより取得する
            String file = getUploadDto().getUploadedFile().getName();
            String filename = file.substring(file.lastIndexOf(FILE_SEPARATOR) + 1);
            // DTOへセット
            getUploadDto().setUploadFileName(filename);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            throw new FtlSystemException(ERR_MSG_UPLOAD_ERR, ERR_MSG_DETAIL_TEMP_CREATE);
        }
        
        return getUploadLogic().getPageKeyTemporary(getUploadDto());
    }
    
    /**
     * ファイル書込み処理
     * @return String
     * @throws IOException
     */
    public String upload() throws ApplicationException {
        try {
            //
            UploadedFile uploadedFile = getUploadDto().getUploadedFile();
            // テンポラリファイル
            File tmpFile = new File(getUploadLogic().getTempFileFullPath(getUploadDto()));
            // テンポラリファイルが存在しない場合は、作成する
            if (!tmpFile.exists() || !tmpFile.isFile()) {
                // 事前チェック処理
                getUploadLogic().validate(getUploadDto());
                
                if (uploadedFile != null) {
                    if ("".equals(getUploadLogic().getTempFileName(getUploadDto()))) {
                        createTempFileName();
                    }
                    createTempFile();
                }
            }
            // 書込み処理
            writeFile();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            throw new FtlSystemException(ERR_MSG_UPLOAD_ERR);
        }
        
        return getUploadLogic().getPageKey(getUploadDto());
    }
}