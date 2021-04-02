import { ICategorie } from 'app/shared/model/categorie.model';

export interface IUtilisateur {
  id?: number;
  nom?: string;
  prenom?: string;
  ones?: ICategorie[];
}

export const defaultValue: Readonly<IUtilisateur> = {};
