import { Moment } from 'moment';
import { etat } from 'app/shared/model/enumerations/etat.model';

export interface IFacture {

  id?: number;
  numero?: number;
  date?: string;
  quantite?: number;
  prenom_client?: string;
  nom_client?: string;
  etat?: etat;
}

export const defaultValue: Readonly<IFacture> = {};
