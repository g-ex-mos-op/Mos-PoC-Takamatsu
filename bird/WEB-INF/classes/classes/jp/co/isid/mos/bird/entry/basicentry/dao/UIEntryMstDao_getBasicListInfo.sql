select BR17.ENTRY_CD      
     , BR17.ENTRY_YEAR    
     , BR17.ENTRY_KAI     
     , BR17.ENTRY_TITLE   
     , BR17.ENTRY_PLACE   
     , BASICDT.BASIC_DT_FROM  
     , BASICDT.BASIC_DT_TO    
     , RINTENDT.RINTEN_DT_FROM 
     , RINTENDT.RINTEN_DT_TO   
     , HONBUINPUT.HONBU_INPUT_DT_FROM  
     , HONBUINPUT.HONBU_INPUT_DT_TO  
     , ONERINPUT.ONER_INPUT_DT_FROM  
     , ONERINPUT.ONER_INPUT_DT_TO
     , HONBUOUTPUT.HONBU_OUTPUT_DT_FROM  
     , HONBUOUTPUT.HONBU_OUTPUT_DT_TO
     , ONEROUTPUT.ONER_OUTPUT_DT_FROM  
     , ONEROUTPUT.ONER_OUTPUT_DT_TO
     , BR17.NUMBER_LIMIT  
     , BR17.PLACE_LIMIT  
     , BR17.NOTE  
     , ( 
        case when ONERSU.NUM is null 
        then 0 
        else ONERSU.NUM 
        end 
       ) as ONERSU 
     , ( 
        case when SOMOSIKOMI_NUM.NUM is null 
        then 0 
        else SOMOSIKOMI_NUM.NUM 
        end 
       ) as SOMOSIKOMI_NUM  
     , ( 
        case when MOSIKOMI_NUM.NUM is null 
        then 0 
        else MOSIKOMI_NUM.NUM 
        end 
       ) as MOSIKOMI_NUM,  
/*IF userTypeCd == '02' */
    DISPLAY.FLG as DISPLAY_FLG, 
/*END*/
    EDIT.FLG as EDIT_FLG
  from BR17ENTL BR17 
       join ( select ENTRY_CD
                   , ENTRY_YEAR
                   , ENTRY_KAI
                   , FROM_DT    as BASIC_DT_FROM
                   , TO_DT      as BASIC_DT_TO
                from BR18ENTD
               where USERTYPE_CD = '99'
                 and DAY_KBN     = '01'
            ) BASICDT
         on ( BASICDT.ENTRY_CD   = BR17.ENTRY_CD   and
              BASICDT.ENTRY_YEAR = BR17.ENTRY_YEAR and
              BASICDT.ENTRY_KAI  = BR17.ENTRY_KAI     
            )
       join ( select ENTRY_CD
                   , ENTRY_YEAR
                   , ENTRY_KAI
                   , FROM_DT    as RINTEN_DT_FROM
                   , TO_DT      as RINTEN_DT_TO
                from BR18ENTD
               where USERTYPE_CD = '99'
                 and DAY_KBN     = '02'
            ) RINTENDT
         on ( RINTENDT.ENTRY_CD   = BR17.ENTRY_CD   and
              RINTENDT.ENTRY_YEAR = BR17.ENTRY_YEAR and
              RINTENDT.ENTRY_KAI  = BR17.ENTRY_KAI     
            )
       join ( select ENTRY_CD
                   , ENTRY_YEAR
                   , ENTRY_KAI
                   , FROM_DT    as HONBU_INPUT_DT_FROM
                   , TO_DT      as HONBU_INPUT_DT_TO
                from BR18ENTD
               where USERTYPE_CD = '01'
                 and DAY_KBN     = '04'
            ) HONBUINPUT
         on ( HONBUINPUT.ENTRY_CD   = BR17.ENTRY_CD   and
              HONBUINPUT.ENTRY_YEAR = BR17.ENTRY_YEAR and
              HONBUINPUT.ENTRY_KAI  = BR17.ENTRY_KAI     
            )
       join ( select ENTRY_CD
                   , ENTRY_YEAR
                   , ENTRY_KAI
                   , FROM_DT    as ONER_INPUT_DT_FROM
                   , TO_DT      as ONER_INPUT_DT_TO
                from BR18ENTD
               where USERTYPE_CD = '02'
                 and DAY_KBN     = '04'
            ) ONERINPUT
         on ( ONERINPUT.ENTRY_CD   = BR17.ENTRY_CD   and
              ONERINPUT.ENTRY_YEAR = BR17.ENTRY_YEAR and
              ONERINPUT.ENTRY_KAI  = BR17.ENTRY_KAI     
            )
       join ( select ENTRY_CD
                   , ENTRY_YEAR
                   , ENTRY_KAI
                   , FROM_DT    as HONBU_OUTPUT_DT_FROM
                   , TO_DT      as HONBU_OUTPUT_DT_TO
                from BR18ENTD
               where USERTYPE_CD = '01'
                 and DAY_KBN     = '03'
            ) HONBUOUTPUT
         on ( HONBUOUTPUT.ENTRY_CD   = BR17.ENTRY_CD   and
              HONBUOUTPUT.ENTRY_YEAR = BR17.ENTRY_YEAR and
              HONBUOUTPUT.ENTRY_KAI  = BR17.ENTRY_KAI     
            )
       join ( select ENTRY_CD
                   , ENTRY_YEAR
                   , ENTRY_KAI
                   , FROM_DT    as ONER_OUTPUT_DT_FROM
                   , TO_DT      as ONER_OUTPUT_DT_TO
                from BR18ENTD
               where USERTYPE_CD = '02'
                 and DAY_KBN     = '03'
            ) ONEROUTPUT
         on ( ONEROUTPUT.ENTRY_CD   = BR17.ENTRY_CD   and
              ONEROUTPUT.ENTRY_YEAR = BR17.ENTRY_YEAR and
              ONEROUTPUT.ENTRY_KAI  = BR17.ENTRY_KAI     
            )
       left join ( select ENTRY_CD
                        , ENTRY_YEAR
                        , ENTRY_KAI
                        , COUNT(distinct ONER_CD) as NUM
                     from BT20ENON 
                    where ENTRY_FLG = '1'
                    group by ENTRY_CD
                           , ENTRY_YEAR
                           , ENTRY_KAI 
                 ) ONERSU 
              on ( ONERSU.ENTRY_CD   = BR17.ENTRY_CD   and
                   ONERSU.ENTRY_YEAR = BR17.ENTRY_YEAR and
                   ONERSU.ENTRY_KAI  = BR17.ENTRY_KAI      )
       left join ( select ENTRY_CD
                        , ENTRY_YEAR
                        , ENTRY_KAI
                        , COUNT(STAFF_ID) as NUM
                     from BT22ENKN
                    group by ENTRY_CD
                           , ENTRY_YEAR
                           , ENTRY_KAI
                 ) SOMOSIKOMI_NUM 
              on ( SOMOSIKOMI_NUM.ENTRY_CD   = BR17.ENTRY_CD   and
                   SOMOSIKOMI_NUM.ENTRY_YEAR = BR17.ENTRY_YEAR and
                   SOMOSIKOMI_NUM.ENTRY_KAI  = BR17.ENTRY_KAI      ) 
       left join ( select ENTRY_CD
                        , ENTRY_YEAR
                        , ENTRY_KAI
                        , count(STAFF_ID) as NUM 
                     from BT22ENKN 
                    where ONER_CD = /*onerCd*/'36478' 
                    group by ENTRY_CD
                        , ENTRY_YEAR
                        , ENTRY_KAI
                 ) MOSIKOMI_NUM
              on ( MOSIKOMI_NUM.ENTRY_CD   = BR17.ENTRY_CD   and
                   MOSIKOMI_NUM.ENTRY_YEAR = BR17.ENTRY_YEAR and
                   MOSIKOMI_NUM.ENTRY_KAI  = BR17.ENTRY_KAI      ) 
    LEFT JOIN (SELECT ENTRY_CD, ENTRY_YEAR, ENTRY_KAI, '1' AS FLG  
               FROM   BR18ENTD
               WHERE  ENTRY_CD = /*entryCd*/'01'
                /*IF userTypeCd == '01' */
                 AND USERTYPE_CD = '01' 
                 AND DAY_KBN = '04' 
                 AND (FROM_DT <= /*sysDate*/'20060605' AND TO_DT >= /*sysDate*/'20060605')
                /*END*/
                /*IF userTypeCd == '02' */
                 AND USERTYPE_CD = '02' 
                 AND DAY_KBN = '04' 
                 AND (FROM_DT <= /*sysDate*/'20060605' AND TO_DT >= /*sysDate*/'20060605')
                /*END*/
              ) EDIT ON  EDIT.ENTRY_CD = BR17.ENTRY_CD
                        AND EDIT.ENTRY_YEAR = BR17.ENTRY_YEAR
                        AND EDIT.ENTRY_KAI = BR17.ENTRY_KAI
/*IF userTypeCd == '02' */
    LEFT JOIN (SELECT ENTRY_CD, ENTRY_YEAR, ENTRY_KAI, '1' AS FLG  
               FROM   BR18ENTD
               WHERE  ENTRY_CD = /*entryCd*/'01'
                 AND  USERTYPE_CD = '02' 
                 AND  DAY_KBN = '04' 
                 AND  TO_DT < /*sysDate*/'20060605'
              ) DISPLAY ON  DISPLAY.ENTRY_CD = BR17.ENTRY_CD
                        AND DISPLAY.ENTRY_YEAR = BR17.ENTRY_YEAR
                        AND DISPLAY.ENTRY_KAI = BR17.ENTRY_KAI
/*END*/
     
 where BR17.ENTRY_CD = /*entryCd*/'01' 
   and BR17.SAKUJO_FLG    = '0'
/*IF userTypeCd == '01' */
   and HONBUOUTPUT.HONBU_OUTPUT_DT_FROM <= /*sysDate*/'20060525' 
   and HONBUOUTPUT.HONBU_OUTPUT_DT_TO   >= /*sysDate*/'20060524'
/*END*/
/*IF userTypeCd == '02' */
   and ONEROUTPUT.ONER_OUTPUT_DT_FROM <= /*sysDate*/'20060525' 
   and ONEROUTPUT.ONER_OUTPUT_DT_TO   >= /*sysDate*/'20060524'
/*END*/ 
 order by BASICDT.BASIC_DT_FROM
        , BASICDT.BASIC_DT_TO 
        , HONBUINPUT.HONBU_INPUT_DT_FROM
        , HONBUINPUT.HONBU_INPUT_DT_TO