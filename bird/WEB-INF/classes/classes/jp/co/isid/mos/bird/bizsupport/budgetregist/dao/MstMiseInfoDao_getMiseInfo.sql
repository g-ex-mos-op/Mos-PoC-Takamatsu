SELECT
    BM01.MISE_CD,
    rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ,
    case when BM58.SIBU_CD is null then '' else BM58.SIBU_CD end as SIBU_CD,
    case when BM57.SIBU_NAME is null then '' else rtrim(BM57.SIBU_NAME) end as SIBU_NAME,
    case when BM58.AREA_DAI is null then '' else BM58.AREA_DAI end as AREA_DAI,
    case when BM57AREA.SIBU_NAME is null then '' else rtrim(BM57AREA.SIBU_NAME) end as SIBU_TORIKOMI_NAME,
    (case when substr(BM01.MISE_KBN,2,1) = '1' then 'FC'
          else (case when substr(BM01.MISE_KBN,2,1) = '2' then 'RC' end) end) as FCRC,
    substr(BM01.MISE_KBN,2,1) as MISE_KBN,
    BM01.OPEN_DT
FROM
    BM01TENM BM01
    left join BM58NTSB BM58 on (BM01.COMPANY_CD = BM58.COMPANY_CD and BM01.MISE_CD = BM58.MISE_CD)
    left join BM57NSIB BM57 on (BM58.COMPANY_CD = BM57.COMPANY_CD and BM58.SIBU_CD = BM57.SIBU_CD)
    left join BM57NSIB BM57AREA on (BM58.COMPANY_CD = BM57AREA.COMPANY_CD and BM58.AREA_DAI = BM57AREA.SIBU_CD)
WHERE
     BM01.SIBU_CD <> ''
 AND BM01.AREA_DAI <> ''
/*IF sysdate != null */
 AND BM01.CLOSE_DT >= /*sysdate*/'20061120'
/*END*/    
 AND BM01.COMPANY_CD = /*companyCd*/'00' 
ORDER BY
    SIBU_CD,
    MISE_CD