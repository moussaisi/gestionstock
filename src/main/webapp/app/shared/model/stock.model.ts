import { Moment } from 'moment';
import { IProduit } from 'app/shared/model/produit.model';

export interface IStock {
  id?: number;
  quantite?: number;
  date?: string;
  date_der_modif?: string;
  produit?: IProduit;
}

export const defaultValue: Readonly<IStock> = {};
