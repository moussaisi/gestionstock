import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProduit } from 'app/shared/model/produit.model';
import { getEntities as getProduits } from 'app/entities/produit/produit.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { getEntities as getFactures } from 'app/entities/facture/facture.reducer';
import { getEntity, updateEntity, createEntity, reset } from './sortie.reducer';
import { ISortie } from 'app/shared/model/sortie.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISortieUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SortieUpdate = (props: ISortieUpdateProps) => {
  const [produitId, setProduitId] = useState('0');
  const [factureId, setFactureId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { sortieEntity, produits, factures, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/sortie' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProduits();
    props.getFactures();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.date = convertDateTimeToServer(values.date);

    if (errors.length === 0) {
      const entity = {
        ...sortieEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gestionStockApp.sortie.home.createOrEditLabel">Create or edit a Sortie</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : sortieEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="sortie-id">ID</Label>
                  <AvInput id="sortie-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="sortie-date">
                  Date
                </Label>
                <AvInput
                  id="sortie-date"
                  type="datetime-local"
                  className="form-control"
                  name="date"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.sortieEntity.date)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="quantiteLabel" for="sortie-quantite">
                  Quantite
                </Label>
                <AvField id="sortie-quantite" type="string" className="form-control" name="quantite" />
              </AvGroup>
              <AvGroup>
                <Label for="sortie-produit">Produit</Label>
                <AvInput id="sortie-produit" type="select" className="form-control" name="produit.id">
                  <option value="" key="0" />
                  {produits
                    ? produits.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.libelle_produit}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="sortie-facture">Facture</Label>
                <AvInput id="sortie-facture" type="select" className="form-control" name="facture.id">
                  <option value="" key="0" />
                  {factures
                    ? factures.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.numero}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/sortie" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  produits: storeState.produit.entities,
  factures: storeState.facture.entities,
  sortieEntity: storeState.sortie.entity,
  loading: storeState.sortie.loading,
  updating: storeState.sortie.updating,
  updateSuccess: storeState.sortie.updateSuccess,
});

const mapDispatchToProps = {
  getProduits,
  getFactures,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SortieUpdate);
