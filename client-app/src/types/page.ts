export interface Page<T> {
  totalPages: number;
  number: number;
  first: boolean;
  last: boolean;
  content: T[];
}
