/*
 * 作成日: 2005/11/29
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import jp.co.isid.mos.bird.framework.entity.FileAccessInfoEntity;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.FullTextSearchLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
//import com.ultraseek.xpa.search.Query;
//import com.ultraseek.xpa.search.QueryNotSupportedException;
//import com.ultraseek.xpa.search.SearchResult;
//import com.ultraseek.xpa.search.SearchResultList;
//import com.ultraseek.xpa.search.SearchServer;
import net.n2sm.search.client.api.Query;
import net.n2sm.search.client.api.QueryNotSupportedException;
import net.n2sm.search.client.api.SearchResult;
import net.n2sm.search.client.api.SearchResultList;
import net.n2sm.search.client.api.SearchServer;

/**
 * FullTextSearch検索ロジック
 * @author xnkusama
 */
public class FullTextSearchLogicImpl implements FullTextSearchLogic {

//    /** ULTRASEEK Server Name */
//    public static final String ULTRASEEK_HOSTNAME = "iblx13";
//    /** ULTRASEEK Server Port */
//    public static final int ULTRASEEK_HOSTPORT = 80;
    /** N2 Search Server(AP) Name */
    public static final String N2SEARCH_HOSTNAME_AP = "n2sap1";
    /** N2 Search Server(DEV) Name */
    public static final String N2SEARCH_HOSTNAME_DEV = "n2s-dev1";
    /** N2 Search Server(AP) Nameの先頭部分 */
    public static final String N2SEARCH_HOSTNAME_HEAD = "emsap";
    /** N2 Search Server Port */
    public static final int N2SEARCH_HOSTPORT = 80;
    /* エラーメッセージ */
    private static final String ERR_MSG_SYSTEMERR = "FullTextSearch検索処理";

//    /**
//     * Ultraseek検索処理
//     * @param UltraseekDto Ultraseek検索用DTO
//     */
//    public void search(UltraseekDto ultraseekDto) throws FtlSystemException {
//        try {
//            SearchResultList searchResultList;
//            // サーバー情報設定
//            SearchServer searchServer = new UltraseekServer(ULTRASEEK_HOSTNAME, ULTRASEEK_HOSTPORT);
//            // 検索対象コレクションを取得
//            SearchCollection searchCollection = searchServer.getSearchCollection(ultraseekDto.getCollectionId());
//            // 検索文字列作成
//            String queryText = makeQueryText(ultraseekDto.getSearchWord());
//            Query query = Query.parse(queryText, Locale.JAPAN);
//
//            if (ultraseekDto.getSearchKikanFrom() != null ||
//                    ultraseekDto.getSearchKikanTo() != null)
//            {
//                Query queryKikan
//                        = new DateRangeQuery(makeKikanDate(ultraseekDto, true), makeKikanDate(ultraseekDto, false));
//                query = new FilterQuery(new Query[]{query, queryKikan});
//            }
//            //検索対象コレクション内から検索実行
//            searchResultList = searchCollection.search(query);
//            // 検索結果セット
//            ultraseekDto.setSearchResultList(searchResultList);
//        }
//        catch (QueryNotSupportedException qnse) {
//            throw new FtlSystemException(ERR_MSG_SYSTEMERR, null, qnse);
//        }
//        catch (IOException ioe) {
//            throw new FtlSystemException(ERR_MSG_SYSTEMERR, null, ioe);
//        }
//
//    }
    /**
     * N2 Search検索処理
     *
     * @param FullTextSearchDto
     *            N2Search検索用DTO
     */
    public void search(FullTextSearchDto fullTextSearchDto) throws FtlSystemException {
        try {
            SearchResultList searchResultList;

            // サーバー情報設定
            SearchServer searchServer = null;

            if (N2SEARCH_HOSTNAME_HEAD.equals(InetAddress.getLocalHost().getHostName().substring(0, 5).toLowerCase())) {
                searchServer = new SearchServer(N2SEARCH_HOSTNAME_AP, N2SEARCH_HOSTPORT);
            }else {
                searchServer = new SearchServer(N2SEARCH_HOSTNAME_DEV, N2SEARCH_HOSTPORT);
            }

            searchServer.setContextPath("/");

            // 検索クエリ
            Query.Builder queryBuilder = new Query.Builder(
                    fullTextSearchDto.getSearchWord(), Locale.JAPAN)
                    // 検索対象コレクションを取得
                    .collection(fullTextSearchDto.getCollectionId());
            if (fullTextSearchDto.getSearchKikanFrom() != null
                    || fullTextSearchDto.getSearchKikanTo() != null) {
                queryBuilder.dateRange(makeKikanDate(fullTextSearchDto, true),
                        makeKikanDate(fullTextSearchDto, false));
            }

            // 検索対象コレクション内から検索実行
            searchResultList = queryBuilder.build().search(
                    searchServer);
            // 検索結果セット
            fullTextSearchDto.setSearchResultList(searchResultList);
        } catch (QueryNotSupportedException qnse) {
            throw new FtlSystemException(ERR_MSG_SYSTEMERR, null, qnse);
        } catch (IOException ioe) {
            throw new FtlSystemException(ERR_MSG_SYSTEMERR, null, ioe);
        }

    }

    /*
     * 検索用検索文字列生成取得処理.
     * <P>
     * @return String 検索用検索文字列生成文字
     */
    private String makeQueryText(final String searchWord) throws IOException {
        boolean found = false;
        StringBuffer sb = new StringBuffer(128);
        String tx = searchWord;
        tx = tx.trim();
        int len = tx.length();
        if (len > 0) {
            found = true;
            sb.append('+');
            for (int j = 0; j < len; j++) {
                char c = tx.charAt(j);
                if (c != '"'){
                    if(c == ' ' || c == '　'){
                        if((sb.toString()).length() > 1) {
                            int nextJ = j + 1;
                            if(nextJ < len) {
                                if(tx.charAt(nextJ) != '　' && tx.charAt(nextJ) != ' ') {
                                    sb.append(' ');
                                    sb.append('+');
                                }
                            }
                        }
                    }
                    else {
                        sb.append(c);
                    }
                }
            }
        }
        return (found) ? sb.toString() : null;
    }

    /*
     * 検索条件の期間From～Toまたは時間From～ToをDate型に変換
     * 期間：yyyyMMdd型
     * 時間：hhmmdd型（24時間制）
     */
    private Date makeKikanDate(final FullTextSearchDto dto, final boolean mode) {
        String date = "";
        String time = "";
        if (mode) {
            date = dto.getSearchKikanFrom();
            time = dto.getSearchKikanTimeFrom();
        }
        else {
            date = dto.getSearchKikanTo();
            time = dto.getSearchKikanTimeTo();
        }
        Calendar cal = Calendar.getInstance(Locale.JAPAN);
        if (date == null || "".equals(date.trim())) {
            return null;
        }
        else {
            cal.set(Calendar.YEAR, getDateIntValue(date, DateFormatter.PATTERN_YEAR));
            cal.set(Calendar.MONTH, getDateIntValue(date, "MM") - 1);
            cal.set(Calendar.DAY_OF_MONTH, getDateIntValue(date, "dd"));
        }
        if (time == null) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
        }
        else {
            cal.set(Calendar.HOUR_OF_DAY, new Integer(time.substring(0, 2)).intValue());
            cal.set(Calendar.MINUTE, new Integer(time.substring(2, 4)).intValue());
            cal.set(Calendar.SECOND, new Integer(time.substring(4)).intValue());
        }
        return cal.getTime();
    }

    /*
     * 日付文字列を数値に変換
     * @param date 変換対象の日付文字列
     * @param mode 変換する日付文字列のフォーマット
     * @return
     */
    private int getDateIntValue(final String date, final String mode) {
        DateFormatter formatter = new DateFormatter();

        return Integer.valueOf(formatter.format(date, mode, DateFormatter.DATE_TYPE_YMD)).intValue();
    }

    /**
     * FullTextSearch検索結果とDB検索結果のマッチングを行う
     * @param dto  FullTextSearch検索結果
     * @param dbData DB検索結果（FileAccessInfoEntityを格納したList）
     * @return List マッチングの結果リスト
     */
    public void matchingData(FullTextSearchDto dto) throws FtlSystemException {
        List matchingResult = new ArrayList();
        try {
            SearchResultList resultList = dto.getSearchResultList();
            for (int i = 0; i < dto.getFileAccessInfoEntity().size(); i++) {
                FileAccessInfoEntity entity = (FileAccessInfoEntity) dto.getFileAccessInfoEntity().get(i);
                for (int j = 0; j < dto.getResultCount(); j++) {
                    SearchResult result = (SearchResult) resultList.get(j);
                    String fullTextSearchFileUrl = result.getUrlLink();
                    String fullTextSearchFileName = fullTextSearchFileUrl.substring(fullTextSearchFileUrl.lastIndexOf("/") + 1);

                    if (entity.getMatchingFileName() != null && entity.getMatchingFileName().trim().length() >= 15
                            && fullTextSearchFileName != null && fullTextSearchFileName.length() >= 15) {
                        if (entity.getMatchingFileName().trim().substring(0, 15)
                                .equals(fullTextSearchFileName.substring(0, 15))) {
                            // System.out.println("☆☆☆ Matching ☆☆☆ -->
                            // EntityFileName:["+entity.getMatchingFileName()+"]
                            // UltraseekFileName["+ultraseekFileName+"]");
                            // entity.setDescription(getSummaryDesc(dto.getSearchWord(),result.getDescription()));
                            entity.setDescription(result.getDigest());
                            // System.out.println("★★★ Matching ★★★ -->
                            // Description:["+entity.getDescription()+"]");
                            matchingResult.add(entity);
                            break;
                        }
                    }

                }
            }
        }
        catch (QueryNotSupportedException qnse) {
            throw new FtlSystemException(ERR_MSG_SYSTEMERR, null, qnse);
        }
        catch (IOException ioe) {
            throw new FtlSystemException(ERR_MSG_SYSTEMERR, null, ioe);
        }

        dto.setMatchingData(matchingResult);
    }
}