export interface Member {
    num?: number,
    name: string | "",
    familyName: string | "",
    accessionDate: Date | any,
    nationality: string | "",
    identityDocumentType: string | "",
    identityNumber: string | "",
    competitions: any[],
    huntingList: any[]
}