SELECT
    RTRIM(pm25kkbn.kkbn_cd) AS KKBN_CD,
    RTRIM(pm25kkbn.kkbn_name) AS KKBN_NAME
FROM
    pm25kkbn
ORDER BY pm25kkbn.kkbn_cd