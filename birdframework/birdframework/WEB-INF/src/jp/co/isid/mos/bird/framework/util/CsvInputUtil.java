/*
 * CsvInputUtil.java
 *
 * Created by dzzhu on 2003/07/14
 *
 */

package jp.co.isid.mos.bird.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;

/**
 * ファイル入出力のためのユティリティクラス。 CSVファイル->String[][]
 * @author ISID MOS PROJECT
 * @version 2003/08/19
 */
public final class CsvInputUtil {

    /**
     * デフォルトオプション。 ・データ部分直上の項目タイトルあり <br>
     * ・通常CSVフォーマット(普通のカンマ区切りテキスト) <br>
     */
    public static final int OPTION_DEFAULT = 0x0000;

    /**
     * データ部分直上の一行内容を出力しない／存在しない
     */
    public static final int OPTION_CSV_NO_DATA_HEAD_ROW = 0x0008;

    private static final String META_QUOTA = "%%##20030825";

    private static final String CSV_EXTENTION = ".csv";

    /**
     * Constructor
     */
    private CsvInputUtil() {
    }

    /**
     * CSV(Comma Seperated File)ファイルを解析して二次元配列を作成
     * @param in 読込む先CSVファイルストリームを指定する
     * @param options OPTION_CSV_NO_DATA_HEAD_ROWの組み合わせ値
     * @return String[][] 二次元配列
     * @throws IOException IO error
     */
    public static String[][] loadCSV(final InputStream in, final int options)
            throws IOException {

        //ファイル存在チェック
        if(in.available() == 0){
            throw new FileNotFoundException();
        }

        String[][] data = null;

        byte[] buf = new byte[in.available()];
        in.read(buf);
        String all = new String(buf);

        data = loadCSV(all, options);

        return data;
    }

    /**
     * CSV(Comma Seperated File)ファイルを解析して二次元配列を作成
     * @param all CSV全体内容文字列を指定する
     * @param options OPTION_CSV_NO_DATA_HEAD_ROWの組み合わせ値
     * @return String[][] 二次元配列
     */
    public static String[][] loadCSV(final String all, final int options) {

        /*
         * 前処理 int[] getRowInfo(String str); int[0]:行数 int[1]:最大列数／全体
         */
        int[] rowInfo = getRowInfo(all);

        int maxRows = rowInfo[0];
        int maxCols = rowInfo[1];

        //先頭行を読み込まないとき
        if ((options & OPTION_CSV_NO_DATA_HEAD_ROW) == 0) {
            maxRows--;
        }

        //行数取得、最大列数で初期化
        String[][] data = new String[maxRows][maxCols];

        int iRow = 0;
        int iNextRowBegin = 0;
        int iAllLen = all.length();
        boolean bHeadRowSkiped = false;

        StringTokenizer stRow = new StringTokenizer(all, "\n\r\f", false);
        while (stRow.hasMoreTokens()) {
            String sRowData = stRow.nextToken();

            if ((options & OPTION_CSV_NO_DATA_HEAD_ROW) == 0 && !bHeadRowSkiped) {
                bHeadRowSkiped = true;
                continue;
            }

            /**
             * parse nextRow
             */
            String[] colDatas = parseCSVRowData(sRowData, maxCols);

            for (int j = 0; j < colDatas.length; j++) {
                data[iRow][j] = colDatas[j];
            }

            iRow++;
        }
        if (data == null) {
            return new String[0][0];
        }
        return data;
    }

    /**
     * CSV(Comma Seperated File)ファイルを解析して二次元配列を作成
     * @param uploadedFile CSVファイル
     * @param options OPTION_CSV_NO_DATA_HEAD_ROWの組み合わせ値
     * @param checkExtension true:拡張子チェックを行う false:拡張子チェックを行わない
     * @return String[][] 二次元配列
     */
    public static String[][] loadCSV(final UploadedFile uploadedFile,
            final int options, final boolean checkExtension) {
        String fileName = uploadedFile.getName();

        //拡張子チェック
        if(checkExtension){
            if(!checkCSVExtension(fileName)){
                throw new InvalidInputException("ファイル拡張子");
            }
        }
        String[][] data = null;

        try{
            data =  loadCSV(uploadedFile.getInputStream(), options);

        } catch (ApplicationException aex) {
            throw aex;

        } catch (Exception ex) {
            throw new FtlSystemException("CSV読み込み", ex.toString(), ex);
        }
        return data;
    }

    /**
     * ファイル拡張子のチェックを行います。
     * @param fileName ファイル名
     * @return true:OK false:NG
     */
    public static boolean checkCSVExtension(final String fileName) {
        if(fileName == null || fileName.length() <= CSV_EXTENTION.length()){
            return  false;
        }
        return fileName.endsWith(CSV_EXTENTION);
    }

    /**
     * CSV(Comma Seperated File)ファイルを解析して、行数・最大列数を取得する
     * @param all CSV全体内容文字列を指定する
     * @return int[] [0]：行数、[1]：最大列数
     * @author xnorsato
     * @since 2004/04/23
     */
    private static int[] getRowInfo(final String all) {
        int rowCount = 0; //行数
        int maxColumn = 0; //最大列数

        StringTokenizer stRow = new StringTokenizer(all, "\n\r\f", false);
        while (stRow.hasMoreTokens()) {
            String row = stRow.nextToken();

            int commaCount = 0;
            int index = row.indexOf(",");
            while (index != -1) {
                //カンマ／行のカウント
                commaCount++;
                index = row.indexOf(",", index + 1);
            }

            //最大カンマ数／行の保持
            if (maxColumn < commaCount) {
                maxColumn = commaCount;
            }

            //行数のカウント
            rowCount++;
        }

        return new int[] { rowCount, maxColumn + 1 };
    }

    /**
     * CSV(コンマ区切りファイル)フォーマットの一行データを分析する
     * @param sRowData 一行データ
     * @return String[] 各コラム内容の配列
     */
    public static String[] parseCSVRowData(final String sRowData) {
        String[][] colDatas = null;

        int iCol = 0;
        int iNextColBegin = 0;
        int iRowLen = sRowData.length();

        while (iNextColBegin < iRowLen) {
            String cell = "";
            int iComma;

            /**
             * "で始まる場合 try to find next ", or last "
             */
            if (sRowData.charAt(iNextColBegin) == '"') {
                /**
                 * "で終わる場合
                 */
                int iQuota = sRowData.indexOf('"', iNextColBegin + 1);
                if (iQuota != -1) {
                    /**
                     * 普通の場合は found ", または last "
                     */
                    if (iQuota + 1 == iRowLen
                            || sRowData.charAt(iQuota + 1) == ',') {
                        cell = sRowData.substring(iNextColBegin + 1, iQuota);
                        iNextColBegin = iQuota + 2;

                        cell = replaceString(cell, "\"\"", "\"");
                    } else {
                        String restRowData;
                        /**
                         * ダブルクオーター "" を " に変換
                         */
                        if (sRowData.charAt(iQuota + 1) == '"') {
                            restRowData = sRowData.substring(iNextColBegin,
                                    iQuota)
                                    + META_QUOTA
                                    + sRowData.substring(iQuota + 2);
                        }
                        /**
                         * 極端の場合は"で終わる この時は両端の"を削除して再分析する
                         */
                        else {
                            restRowData = sRowData.substring(iNextColBegin + 1,
                                    iQuota)
                                    + sRowData.substring(iQuota + 1);
                        }

                        String[] restColDatas = parseCSVRowData(restRowData);

                        if (colDatas == null) {
                            return restColDatas;
                        } else {
                            String[] newColDatas = new String[iCol
                                    + restColDatas.length];
                            for (int j = 0; j < iCol; j++) {
                                newColDatas[j] = colDatas[0][j];
                            }
                            for (int j = 0; j < restColDatas.length; j++) {
                                newColDatas[iCol + j] = restColDatas[j];
                            }
                            return newColDatas;
                        }
                    }
                }
                /**
                 * "で終わらない場合
                 */
                else {
                    cell = sRowData.substring(iNextColBegin + 1);
                    iNextColBegin = iRowLen;
                }
            }
            /**
             * ="で始まる場合 (Excel計算式) try to find next ", or , or last "
             */
            else if (iNextColBegin + 2 <= iRowLen
                    && sRowData.substring(iNextColBegin, iNextColBegin + 2)
                            .equals("=\"")) {

                iComma = sRowData.indexOf(',', iNextColBegin + 2);
                if (iComma != -1) {
                    /**
                     * ",で終わる場合
                     */
                    if (iComma >= iNextColBegin + 3
                            && sRowData.charAt(iComma - 1) == '"') {
                        cell = sRowData
                                .substring(iNextColBegin + 2, iComma - 1);

                        cell = replaceString(cell, "\"&CHAR(44)&\"", ",");
                        cell = replaceString(cell, "\"\"", "\"");
                    }
                    /**
                     * ,で終わる場合
                     */
                    else {
                        cell = sRowData.substring(iNextColBegin, iComma);
                    }
                    iNextColBegin = iComma + 1;
                }
                /**
                 * "で終わる場合
                 */
                else if (sRowData.charAt(iRowLen - 1) == '"') {
                    cell = sRowData.substring(iNextColBegin + 2, iRowLen - 1);
                    iNextColBegin = iRowLen;

                    cell = replaceString(cell, "\"&CHAR(44)&\"", ",");
                    cell = replaceString(cell, "\"\"", "\"");
                }
                /**
                 * その他場合
                 */
                else {
                    cell = sRowData.substring(iNextColBegin);
                    iNextColBegin = iRowLen;
                }
            }
            /**
             * 一番普通の場合
             */
            else {
                iComma = sRowData.indexOf(',', iNextColBegin);
                if (iComma != -1) {
                    cell = sRowData.substring(iNextColBegin, iComma);
                    iNextColBegin = iComma + 1;
                } else {
                    cell = sRowData.substring(iNextColBegin);
                    iNextColBegin = iRowLen;
                }
            }

            cell = replaceString(cell, META_QUOTA, "\"");

            colDatas = extendArray2(colDatas, 1, iCol, 1, iCol + 1);
            colDatas[0][iCol] = cell;

            iCol++;
        }

        if (colDatas == null) {
            return new String[] { null };
        } else {
            return shrinkArray2(colDatas, 1, iCol)[0];
        }
    }

    /**
     * CSV(コンマ区切りファイル)フォーマットの一行データを分析する
     * @param sRowData 一行データ
     * @param maxCol 最大列数
     * @return String[] 各コラム内容の配列
     */
    public static String[] parseCSVRowData(final String sRowData,
            final int maxCol) {
        String[] colData = new String[maxCol];

        int iCol = 0;
        int iNextColBegin = 0;
        int iRowLen = sRowData.length();

        while (iNextColBegin < iRowLen) {
            String cell = "";
            int iComma;

            /**
             * "で始まる場合 try to find next ", or last "
             */
            if (sRowData.charAt(iNextColBegin) == '"') {
                /**
                 * "で終わる場合
                 */
                int iQuota = sRowData.indexOf('"', iNextColBegin + 1);
                if (iQuota != -1) {
                    /**
                     * 普通の場合は found ", または last "
                     */
                    if (iQuota + 1 == iRowLen
                            || sRowData.charAt(iQuota + 1) == ',') {
                        cell = sRowData.substring(iNextColBegin + 1, iQuota);
                        iNextColBegin = iQuota + 2;

                        cell = replaceString(cell, "\"\"", "\"");
                    } else {
                        String restRowData;
                        /**
                         * ダブルクオーター "" を " に変換
                         */
                        if (sRowData.charAt(iQuota + 1) == '"') {
                            restRowData = sRowData.substring(iNextColBegin,
                                    iQuota)
                                    + META_QUOTA
                                    + sRowData.substring(iQuota + 2);
                        }
                        /**
                         * 極端の場合は"で終わる この時は両端の"を削除して再分析する
                         */
                        else {
                            restRowData = sRowData.substring(iNextColBegin + 1,
                                    iQuota)
                                    + sRowData.substring(iQuota + 1);
                        }

                        String[] restColDatas = parseCSVRowData(restRowData,
                                maxCol);

                        if (colData == null) {
                            return restColDatas;
                        } else {
                            String[] newColDatas = new String[maxCol];
                            for (int j = 0; j < iCol; j++) {
                                newColDatas[j] = colData[j];
                            }
                            for (int j = 0; j < restColDatas.length - iCol; j++) {
                                newColDatas[iCol + j] = restColDatas[j];
                            }
                            return newColDatas;
                        }
                    }
                }
                /**
                 * "で終わらない場合
                 */
                else {
                    cell = sRowData.substring(iNextColBegin + 1);
                    iNextColBegin = iRowLen;
                }
            }
            /**
             * ="で始まる場合 (Excel計算式) try to find next ", or , or last "
             */
            else if (iNextColBegin + 2 <= iRowLen
                    && sRowData.substring(iNextColBegin, iNextColBegin + 2)
                            .equals("=\"")) {

                iComma = sRowData.indexOf(',', iNextColBegin + 2);
                if (iComma != -1) {
                    /**
                     * ",で終わる場合
                     */
                    if (iComma >= iNextColBegin + 3
                            && sRowData.charAt(iComma - 1) == '"') {
                        cell = sRowData
                                .substring(iNextColBegin + 2, iComma - 1);

                        cell = replaceString(cell, "\"&CHAR(44)&\"", ",");
                        cell = replaceString(cell, "\"\"", "\"");
                    }
                    /**
                     * ,で終わる場合
                     */
                    else {
                        cell = sRowData.substring(iNextColBegin, iComma);
                    }
                    iNextColBegin = iComma + 1;
                }
                /**
                 * "で終わる場合
                 */
                else if (sRowData.charAt(iRowLen - 1) == '"') {
                    cell = sRowData.substring(iNextColBegin + 2, iRowLen - 1);
                    iNextColBegin = iRowLen;

                    cell = replaceString(cell, "\"&CHAR(44)&\"", ",");
                    cell = replaceString(cell, "\"\"", "\"");
                }
                /**
                 * その他場合
                 */
                else {
                    cell = sRowData.substring(iNextColBegin);
                    iNextColBegin = iRowLen;
                }
            }
            /**
             * 一番普通の場合
             */
            else {
                iComma = sRowData.indexOf(',', iNextColBegin);
                if (iComma != -1) {
                    cell = sRowData.substring(iNextColBegin, iComma);
                    iNextColBegin = iComma + 1;
                } else {
                    cell = sRowData.substring(iNextColBegin);
                    iNextColBegin = iRowLen;
                }
            }

            cell = replaceString(cell, META_QUOTA, "\"");

            colData[iCol] = cell;

            iCol++;
        }

        if (colData == null) {
            return new String[] { null };
        } else {
            return colData;
        }
    }

    private static String[][] extendArray2(final String[][] oldArray,
            final int oldRows, final int oldCols, final int rows, final int cols) {

        if (oldArray == null) {
            return new String[rows][cols];
        }

        if (rows < oldArray.length && cols < oldArray[0].length) {
            return oldArray;
        }

        String[][] array = new String[rows + 50][cols + 5];
        for (int i = 0; i < oldArray.length && i < oldRows; i++) {
            System.arraycopy(oldArray[i], 0, array[i], 0, Math.min(
                    oldArray[0].length, oldCols));
        }

        return array;
    }

    private static String[][] shrinkArray2(final String[][] oldArray,
            final int rows, final int cols) {
        if (oldArray == null) {
            return oldArray;
        }

        if (rows >= oldArray.length && cols >= oldArray[0].length) {
            return oldArray;
        }

        String[][] array = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(oldArray[i], 0, array[i], 0, cols);
        }

        return array;
    }

    private static String replaceString(final String string,
            final String sSearch, final String sReplace) {

        String result = "";
        int iNextPos = 0;
        while (iNextPos < string.length()) {
            int i = string.indexOf(sSearch, iNextPos);
            if (i == -1) {
                if (result.length() == 0) {
                    return string;
                }
                result += string.substring(iNextPos);
                break;
            } else {
                result += string.substring(iNextPos, i) + sReplace;
                iNextPos = i + sSearch.length();
            }
        }

        return result;
    }

}