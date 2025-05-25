export class ColumnUtil {
  header: string;
  field: string;
  disableSort?: boolean;
  sortField?: string;
  columnWidth?: string;
  type?: string;
  pipe?: string;
  columnUpdated? = false;
  hide?: boolean;
  visible?: boolean;
  roles?: string[] = [];
  opcao?: any = {};
}
