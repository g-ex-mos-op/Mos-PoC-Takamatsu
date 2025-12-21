SELECT  URI_KEN_0000
   ,    URI_KIN_0000
   ,    URI_KEN_0100
   ,    URI_KIN_0100
   ,    URI_KEN_0200
   ,    URI_KIN_0200
   ,    URI_KEN_0300
   ,    URI_KIN_0300
   ,    URI_KEN_0400
   ,    URI_KIN_0400
   ,    URI_KEN_0500
   ,    URI_KIN_0500
   ,    URI_KEN_0600
   ,    URI_KIN_0600
   ,    URI_KEN_0700
   ,    URI_KIN_0700
   ,    URI_KEN_0800
   ,    URI_KIN_0800
   ,    URI_KEN_0900
   ,    URI_KIN_0900
   ,    URI_KEN_1000
   ,    URI_KIN_1000
   ,    URI_KEN_1100
   ,    URI_KIN_1100
   ,    URI_KEN_1200
   ,    URI_KIN_1200
   ,    URI_KEN_1300
   ,    URI_KIN_1300
   ,    URI_KEN_1400
   ,    URI_KIN_1400
   ,    URI_KEN_1500
   ,    URI_KIN_1500
   ,    URI_KEN_1600
   ,    URI_KIN_1600
   ,    URI_KEN_1700
   ,    URI_KIN_1700
   ,    URI_KEN_1800
   ,    URI_KIN_1800
   ,    URI_KEN_1900
   ,    URI_KIN_1900
   ,    URI_KEN_2000
   ,    URI_KIN_2000
   ,    URI_KEN_2100
   ,    URI_KIN_2100
   ,    URI_KEN_2200
   ,    URI_KIN_2200
   ,    URI_KEN_2300
   ,    URI_KIN_2300
   ,    URI_KEN_OTHER
   ,    URI_KIN_OTHER
FROM    BT78JRAL BT78
        INNER JOIN BM01TENM BM01
                ON (BT78.COMPANY_CD = BM01.COMPANY_CD
               AND  BT78.MISE_CD = BM01.MISE_CD) 
WHERE   DATA_NUM = '00'
AND     BT78.MISE_CD = /*miseCd*/'02001'
AND     SHU_SYS_DT = /*latestDate*/'20070202'
AND     BT78.COMPANY_CD = /*companyCd*/'00'