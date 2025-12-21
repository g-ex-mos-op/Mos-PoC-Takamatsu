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
        case when ENUM.NUM is null 
        then 0 
        else ENUM.NUM 
        end 
       ) as ENTRY_NUM
     , (
        case when ENUM.NUM < BR17.NUMBER_LIMIT
        then ''
        else (case when ENUM.NUM is null
              then ''
              else '–ž'
              end
             )
        end
       ) as MANIN_KBN
       
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
                        , COUNT(ONER_CD) as NUM
                     from BT20ENON 
                    group by ENTRY_CD
                           , ENTRY_YEAR
                           , ENTRY_KAI 
                 ) EONUM 
              on ( EONUM.ENTRY_CD   = BR17.ENTRY_CD   and
                   EONUM.ENTRY_YEAR = BR17.ENTRY_YEAR and
                   EONUM.ENTRY_KAI  = BR17.ENTRY_KAI      )
       left join ( select ENTRY_CD
                        , ENTRY_YEAR
                        , ENTRY_KAI
                        , COUNT(STAFF_ID) as NUM
                     from BT22ENKN
                    group by ENTRY_CD
                           , ENTRY_YEAR
                           , ENTRY_KAI
                 ) ENUM 
              on ( ENUM.ENTRY_CD   = EONUM.ENTRY_CD   and
                   ENUM.ENTRY_YEAR = EONUM.ENTRY_YEAR and
                   ENUM.ENTRY_KAI  = EONUM.ENTRY_KAI      ) 
     , ( select count(DISTINCT ONER.ONER_CD) as NUM 
           from BM11ONER ONER
              , BM01TENM BM01 
          where KEIYAKU_STA <= /*sysDate*/'20060524' 
            and KEIYAKU_END >= /*sysDate*/'20060524' 
            and ONER.ONER_CD = BM01.ONER_CD 
       ) BM11
     
 where BR17.ENTRY_CD = /*entryCd*/'01' 
   and BR17.SAKUJO_FLG    = '0'
   and HONBUOUTPUT.HONBU_OUTPUT_DT_FROM <= /*sysDate*/'20060524'
   and HONBUOUTPUT.HONBU_OUTPUT_DT_TO   >= /*sysDate*/'20060524'
 order by BASICDT.BASIC_DT_FROM
        , BASICDT.BASIC_DT_TO 
        , HONBUINPUT.HONBU_INPUT_DT_FROM
        , HONBUINPUT.HONBU_INPUT_DT_TO
