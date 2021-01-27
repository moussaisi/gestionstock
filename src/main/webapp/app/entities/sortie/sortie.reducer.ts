import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISortie, defaultValue } from 'app/shared/model/sortie.model';

export const ACTION_TYPES = {
  FETCH_SORTIE_LIST: 'sortie/FETCH_SORTIE_LIST',
  FETCH_SORTIE: 'sortie/FETCH_SORTIE',
  CREATE_SORTIE: 'sortie/CREATE_SORTIE',
  UPDATE_SORTIE: 'sortie/UPDATE_SORTIE',
  DELETE_SORTIE: 'sortie/DELETE_SORTIE',
  RESET: 'sortie/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISortie>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type SortieState = Readonly<typeof initialState>;

// Reducer

export default (state: SortieState = initialState, action): SortieState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SORTIE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SORTIE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SORTIE):
    case REQUEST(ACTION_TYPES.UPDATE_SORTIE):
    case REQUEST(ACTION_TYPES.DELETE_SORTIE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SORTIE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SORTIE):
    case FAILURE(ACTION_TYPES.CREATE_SORTIE):
    case FAILURE(ACTION_TYPES.UPDATE_SORTIE):
    case FAILURE(ACTION_TYPES.DELETE_SORTIE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SORTIE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_SORTIE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SORTIE):
    case SUCCESS(ACTION_TYPES.UPDATE_SORTIE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SORTIE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/sorties';

// Actions

export const getEntities: ICrudGetAllAction<ISortie> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SORTIE_LIST,
    payload: axios.get<ISortie>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ISortie> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SORTIE,
    payload: axios.get<ISortie>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISortie> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SORTIE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISortie> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SORTIE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISortie> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SORTIE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
