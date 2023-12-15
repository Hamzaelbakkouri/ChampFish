export interface ranking {
    id: {
        competition_code: string | '',
        member_num: number
    },
    rank: number | 0,
    score: number | 0
}

export interface rankingNoCompetition {

    id: {
        member: {
            num: 1,
            name: string | '',
            familyName: string | '',
            nationality: string | '',
            identityDocumentType: string | '',
            identityNumber: string | ''
        }
    }
    rank: number | 0
    score: number | 0
}