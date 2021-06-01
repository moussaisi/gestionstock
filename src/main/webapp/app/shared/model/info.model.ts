export interface IInfo {
  id?: number;
  nom?: string;
  prenom?: string;
  etablissement?: string;
}

export const defaultValue: Readonly<IInfo> = {};
