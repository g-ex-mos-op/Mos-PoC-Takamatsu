UPDATE BT49LONG
SET    MISE_CD = /*uIOfferEntry.miseCd*/
,      L_NAME_KJ = /*uIOfferEntry.lNameKj*/
,      F_NAME_KJ = /*uIOfferEntry.fNameKj*/
,      L_NAME_RM = /*uIOfferEntry.lNameRm*/
,      F_NAME_RM = /*uIOfferEntry.fNameRm*/
,      SEX = /*uIOfferEntry.sex*/
,      BIRTHDAY = /*uIOfferEntry.birthday*/
,      AGE = /*uIOfferEntry.age*/
,      ENTRY_DATE = /*uIOfferEntry.entryDate*/
,      EXP_KBN = /*uIOfferEntry.expKbn*/
,      LAST_USER = /*uIOfferEntry.lastUser*/'99990003'
,      LAST_PGM = /*uIOfferEntry.lastPgm*/'BEN017L04'
,      LAST_TMSP = /*uIOfferEntry.lastTmsp*/
WHERE 
	ENTRY_CD = /*uIOfferEntry.entryCd*/'00' AND
    ENTRY_YEAR = /*uIOfferEntry.entryYear*/'2007' AND
    ENTRY_KAI = /*uIOfferEntry.entryKai*/'001' AND
    COMPANY_CD = /*uIOfferEntry.companyCd*/'00' AND
    ONER_CD = /*uIOfferEntry.onerCd*/'36478' AND
	SEQ_NO = /*uIOfferEntry.seqNo*/'00'
