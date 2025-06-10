export class FuncoesUtil {

  public static formatarCpf(cpf: string) {
    return cpf.replace(/\D/g, '');
  }

  public static formatarData(data: Date): Date {
    return new Date(data + 'T00:00')
  }

  public static preencherZeros(valor: number, casas: number): string {
    let numeroEmString: string = `${valor}`;
    while (numeroEmString.length < casas) {
      numeroEmString = `0${numeroEmString}`;
    }
    return numeroEmString;
  }

  private static capitalizeWord(texto: string): string {
    return texto[0].toUpperCase() + texto.slice(1, texto.length);
  }

  public static editarFormatoData(data: Date): string {
    return data.toLocaleDateString();
  }

  public static converterLocalDate(date: Date | null): Date | null {
    if (date == null || date.toString().trim() === '') {
      return null;
    }
    return date instanceof Date ? new Date(date.getTime()) : new Date(`${date}T00:00:00-03:00`);
  }

  public static convertToDate(date: Date | string): Date | string{
    const originalDate = new Date(date);
    const year = originalDate.getFullYear();
    const month = (originalDate.getMonth() + 1).toString().padStart(2, '0');
    const day = originalDate.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  public static calcularDataDevolucaoPresvista(dataLocacao: Date, prazoDiasClasse: any): Date {
    const copiaDaData = new Date(dataLocacao);
    copiaDaData.setDate(copiaDaData.getDate() + prazoDiasClasse);
    return copiaDaData;
  }

  public static diferencaDatas(date1: Date, date2: Date): number {
    return (date1.getDate() - date2.getDate()) / (1000 * 60 * 60 * 24);
  }
}
