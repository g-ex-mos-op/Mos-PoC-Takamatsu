SELECT
    BT12.info_shu,
    BT12.reg_date,
    BT12.seq,
    BT12.R_COMPANY_CD,
    BT13.SHOZOKU_KBN,
    BT14.GYOTAI_KBN,
    BT14.KOBETSU_FLG,
    BT14.MISE_FLG,
    BT15.KOBETSU_SHU,
    rtrim(BT15.KOBETSU_CD) as KOBETSU_CD,
    BT16.MISE_CD
FROM
    BT12IACP as BT12,
    BT13IASZ as BT13,
    BM13SHKM as BM13,
    BT14IAGT as BT14
    left join BT15IAID as BT15 
         on (BT14.info_shu = BT15.info_shu AND
             BT14.reg_date = BT15.reg_date AND
             BT14.seq = BT15.seq AND
             BT14.gyotai_kbn = BT15.gyotai_kbn
            )
    left join BT16IAST as BT16 
         on (BT14.info_shu = BT16.info_shu AND
             BT14.reg_date = BT16.reg_date AND
             BT14.seq = BT16.seq AND
             BT14.gyotai_kbn = BT16.gyotai_kbn
            )
WHERE
    bt12.info_shu = bt13.info_shu AND
    bt12.reg_date = bt13.reg_date AND
    bt12.seq = bt13.seq AND
    bt13.info_shu = bt14.info_shu AND
    bt13.reg_date = bt14.reg_date AND
    bt13.seq = bt14.seq AND
    bt13.shozoku_kbn = bm13.shozoku_kbn AND
    bt12.info_shu = /*infoShu*/'03' AND
    bt12.reg_date = /*regDate*/'20070724' AND
    bt12.seq = /*seq*/'0000' AND
    bt12.r_company_cd in (SELECT
                            r_company_cd
                          FROM
                            BM03USCP
                          WHERE user_id = /*userId*/'99990001'
                          ORDER BY
                            r_company_cd
                          ) AND
    bm13.user_id = /*userID*/'99990001' AND
    bt14.gyotai_kbn in (SELECT
                            gyotai_kbn
                        FROM
                            BM05USGT
                        WHERE
                            user_id = /*userId*/'99990001'
                        )