SELECT
    bt12.info_shu,
    bt12.reg_date,
    bt12.seq,
    bt12.r_company_cd,
    bc02.company_name,
    bt12.first_user,
    bt12.first_pgm,
    bt12.first_tmsp,
    bt12.last_user,
    bt12.last_pgm,
    bt12.last_tmsp
FROM
    bt12iacp bt12 inner join bc02comp bc02 on bt12.r_company_cd = bc02.r_company_cd
WHERE bt12.info_shu = /*infoShu*/'01'
AND   bt12.reg_date = /*regDate*/'20060126' 
/*IF seq != null */
    AND  bt12.seq = /*seq*/'0001'
/*END*/
ORDER BY
    bt12.r_company_cd
