select
    count(*)
FROM BT01INFM BT 
,    BR01USER BR01
WHERE BT.REG_DATE like /*pubDate*/'200601%'
AND   BT.CATE_ID = /*cateId*/'001' 
AND   BR01.USER_ID=/*userId*/'99990001'
AND   BT.PUB_ORG = BR01.BUMON_CD
AND   BT.R_COMPANY_CD IN (
		SELECT r_company_cd
		FROM BM03USCP
		WHERE user_id = /*userId*/'99990001'
		)
AND   BT.SAKUJO_FLG <> '1'

