select sub.* 
from 
(
SELECT
   row_number() over(order by BT.reg_date, BT.seq) as ROW_NUM 
  ,'02' as INFO_SHU 
  ,BT.REG_DATE 
  ,BT.SEQ 
  ,BT.CATE_ID 
  ,BM02.CATE_NAME 
  ,'' as SUB_CATE_ID 
  ,'' as SUB_CATE_NAME 
  ,rtrim(BT.TITLE) as TITLE
  ,rtrim(BT.FILE_NAME) as FILE_NAME
FROM BM02IFCT BM02
,    BT02NTCM BT
      
WHERE BM02.INFO_SHU = /*INFO_SHU*/'02'
AND   BT.SAKUJO_FLG <> '1' 
/*IF CATE_ID != null */AND BT.CATE_ID = /*CATE_ID*/'01'/*END*/
/*IF TITLE != null */AND BT.TITLE like /*TITLE*/'%99999999%'/*END*/
/*IF regDate != null */AND BT.REG_DATE like /*regDate*/'999912%'/*END*/
AND   BT.CATE_ID   = BM02.CATE_ID
ORDER BY BM02.SORT_KEY
,        BT.REG_DATE  DESC
,        BT.SEQ DESC
) as sub
