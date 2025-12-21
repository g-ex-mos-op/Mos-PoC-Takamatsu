SELECT
    bm46tmst.taku_cd,
    bm46tmst.taku_name,
    bm47tdms.taku_detail_cd,
    bm47tdms.taku_detail_name
FROM
    bm46tmst JOIN bm47tdms ON (bm46tmst.taku_cd= bm47tdms.taku_cd)
ORDER BY
    bm46tmst.sort_taku, bm47tdms.sort_detail_taku