/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.common;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;

/**
 * ポイント情報取込 共通クラス
 * @author yushuncheng
 *
 */
public class PointInfoTakeinCommon {

    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    private static final String FILE_SEPARATOR_WIN = "\\";

     /**
      * エラーCSVヘッダ
      * @param headerArray
      * @return
      */
     public static List<String> makeHeader(PointInfoTakeinDto dto) {
     	String[] headerArray = null;
     	if(dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_HPRI) {
     		headerArray = PointInfoTakeinConstants.CSV_HEADER_HPRI.split(",");
     	}else if(dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_KAIGAI_HPRI) {
     		headerArray = PointInfoTakeinConstants.CSV_HEADER_KAIGAI_HPRI.split(",");
     	}else if(dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_TSES) {
     		headerArray = PointInfoTakeinConstants.CSV_HEADER_RTESE.split(",");
     	}else if(dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_YPRI) {
     		headerArray = PointInfoTakeinConstants.CSV_HEADER_YPRI.split(",");
     	}

         List<String> listHeader = new ArrayList<String>();
         for(int i=0; i< headerArray.length; i++) {
         	listHeader.add(headerArray[i]);
         }
         if(listHeader.size() > 0) {
         	listHeader.add("エラー内容");
         }

         return listHeader;
     }

     /**
      * CSVエラーファイル名の取得
      * @param csvName
      * @return
      */
     public static String getCsvErrorName(String originalFileName) {
    	 String name = "";
    	 if(originalFileName != null && originalFileName.length() > 0) {
             //ファイル区切り文字判別
             String fileSep = FILE_SEPARATOR_WIN;
             //  ファイル名に「\」があるかで判別
             if (originalFileName.indexOf(FILE_SEPARATOR_WIN) < 0) {
                 fileSep = FILE_SEPARATOR;
             }
             String filename = originalFileName.substring(originalFileName.lastIndexOf(fileSep) + 1);
             name = filename.substring(0, filename.toLowerCase().lastIndexOf(".csv")) + "_ERROR.csv";
     	}
    	 return name;
     }
 }

