export class OpcaoMenuModel {
    constructor(
        public icone: string,
        public tooltip: string,
        public aoClicar: () => void,
    ) {}
}
