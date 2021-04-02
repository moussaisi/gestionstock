import { ICategorie } from 'app/shared/model/categorie.model';

export interface IProduit {
  id?: number;
  libelle_produit?: string;
  categorie?: ICategorie;
}

export const defaultValue: Readonly<IProduit> = {};
