    SELECT MST.CKANRI_NO
    ,      MST.TITLE
    ,      MST.TARGET_FROM
    ,      MST.TARGET_TO
    ,      MST.DEFAULT_FROM
    ,      MST.DEFAULT_TO
    ,      MST.YOBI_FROM
    ,      MST.YOBI_TO
    ,      MST.COMPANY_CD
    ,      MST.COMPANY_NAME
    ,      MST.MISE_CD
    ,      MST.MISE_NAME_KJ
    ,      MST.SHOKU_CD
    ,      MST.SHOKU_NAME_KNA
    ,      DECIMAL(CASE WHEN RESERVE.AMOUNT IS NULL 
                        THEN 0
                        ELSE RESERVE.AMOUNT
                   END) AS RESERVE_AMT
    FROM (
        select BM39.CKANRI_NO
        ,      BM39.TITLE
        ,      BM39.TARGET_FROM
        ,      BM39.TARGET_TO
        ,      BM39.DEFAULT_FROM
        ,      BM39.DEFAULT_TO
        ,      BM39.YOBI_FROM
        ,      BM39.YOBI_TO
        ,      BM01.COMPANY_CD
        ,      BC05.COMPANY_NAME
        ,      BM01.MISE_CD
        ,      BM01.MISE_NAME_KJ
        ,      BM41.SHOKU_CD
        ,      PM06.SHOKU_NAME_KNA
        ,      PM06.IRIME1
        from BM39CPRD BM39
        ,    BM40CMGP BM40
        ,    BM41CMNU BM41
        ,    BM01TENM BM01
        ,    BC05KCOM BC05
        ,    PM06SHOK PM06
        WHERE BM01.COMPANY_CD    = /*companyCd*/'00'
        AND   BM39.CKANRI_NO     = /*ckanriNo*/'200636'
        AND   PM06.SHOKU_CD      = /*shokuCd*/'00088'
        AND   BM01.SIBU_CD       = /*sibuCd*/'031'
        AND   BM40.CKANRI_NO     = BM39.CKANRI_NO
        AND   BM41.CKANRI_NO     = BM40.CKANRI_NO 
        AND   BM41.MENU_GROUP = BM40.MENU_GROUP 
        AND   BM41.SHOKU_CD   = PM06.SHOKU_CD
        AND   BC05.COMPANY_CD = BM01.COMPANY_CD 
        AND   BM01.OPEN_DT <= BM39.TARGET_FROM 
        AND   BM01.CLOSE_DT > BM39.TARGET_FROM 
        group by BM39.CKANRI_NO
        ,        BM39.TITLE
        ,        BM39.TARGET_FROM
        ,        BM39.TARGET_TO
        ,        BM39.DEFAULT_FROM
        ,        BM39.DEFAULT_TO
        ,        BM39.YOBI_FROM
        ,        BM39.YOBI_TO
        ,        BM01.COMPANY_CD
        ,        BC05.COMPANY_NAME
        ,        BM01.MISE_CD
        ,        BM01.MISE_NAME_KJ
        ,        BM41.SHOKU_CD
        ,        PM06.SHOKU_NAME_KNA
        ,        PM06.IRIME1
    ) MST
    LEFT JOIN (
        SELECT BT71.COMPANY_CD
        ,      BT71.MISE_CD
        ,      BT71.CKANRI_NO
        ,      CMNU.SHOKU_CD
        ,      SUM(DECIMAL(BT71.RESERVE_AMT*CMNU.CONV_VALUE)) AS AMOUNT
        FROM (
             SELECT MENU_CD, SHOKU_CD, CONV_VALUE
             FROM BM41CMNU
             WHERE CKANRI_NO= /*ckanriNo*/'200631'
             AND   SHOKU_CD = /*shokuCd*/'00088'
             ) CMNU
        ,    BT70CRSV BT70
        ,    BT71CRSD BT71
        WHERE BT70.CKANRI_NO  = /*ckanriNo*/'200631'
        AND   BT70.COMPANY_CD = /*companyCd*/'00'
        AND   BT70.RESERVE_DT >= /*sysDate*/'20061030'
        AND   BT70.RESERVE_DT >= /*dateFrom*/'20061210'
        AND   BT70.RESERVE_DT <= /*dateTo*/'20061225'
        AND   BT70.CANCEL_FLG <> '1'
        AND   BT71.CKANRI_NO = BT70.CKANRI_NO
        AND   BT71.COMPANY_CD = BT70.COMPANY_CD
        AND   BT70.MISE_CD IN (SELECT MISE_CD 
                               FROM BM01TENM 
                               WHERE COMPANY_CD = /*companyCd*/'00' 
                               AND SIBU_CD = /*sibuCd*/'031' 
                               AND OPEN_DT <= /*dateFrom*/'20061210'
                               AND CLOSE_DT > /*dateFrom*/'20061210')
        AND   BT71.MISE_CD = BT70.MISE_CD
        AND   BT71.SEQ_NO = BT70.SEQ_NO
        AND   BT71.MENU_CD = CMNU.MENU_CD
        GROUP BY BT71.COMPANY_CD
        ,      BT71.MISE_CD
        ,      BT71.CKANRI_NO
        ,      CMNU.SHOKU_CD
        ) RESERVE
    ON ( RESERVE.COMPANY_CD = MST.COMPANY_CD 
     AND RESERVE.MISE_CD    = MST.MISE_CD
     AND RESERVE.CKANRI_NO  = MST.CKANRI_NO
     AND RESERVE.SHOKU_CD = MST.SHOKU_CD
    )
ORDER BY MISE_CD