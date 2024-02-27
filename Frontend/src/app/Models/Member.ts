export interface Member {
    member(member: any): unknown;
    num?: number,
    name: string | "",
    email : string | "",
    familyName: string | "",
    role : string | "",
    accessionDate: Date | any,
    nationality: string | "",
    identityDocumentType: string | "",
    identityNumber: string | "",
    competitions: any[],
    huntingList: any[]
}