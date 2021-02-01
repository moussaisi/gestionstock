import { Moment } from 'moment';
import { IProduit } from 'app/shared/model/produit.model';
import { IFacture } from 'app/shared/model/facture.model';

export interface ISortie {
  id?: number;
  date?: string;
  quantite?: number;
  produit?: IProduit;
  facture?: IFacture;
}

export const defaultValue: Readonly<ISortie> = {};
