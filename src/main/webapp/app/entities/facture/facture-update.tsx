import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './facture.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFactureUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FactureUpdate = (props: IFactureUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { factureEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/facture' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
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
        ...factureEntity,
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
          <h2 id="gestionStockApp.facture.home.createOrEditLabel">Create or edit a Facture</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : factureEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="facture-id">ID</Label>
                  <AvInput id="facture-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="facture-date">
                  Date
                </Label>
                <AvInput
                  id="facture-date"
                  type="datetime-local"
                  className="form-control"
                  name="date"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.factureEntity.date)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="quantiteLabel" for="facture-quantite">
                  Quantite
                </Label>
                <AvField id="facture-quantite" type="string" className="form-control" name="quantite" />
              </AvGroup>
              <AvGroup>
                <Label id="prenom_clientLabel" for="facture-prenom_client">
                  Prenom Client
                </Label>
                <AvField id="facture-prenom_client" type="text" name="prenom_client" />
              </AvGroup>
              <AvGroup>
                <Label id="nom_clientLabel" for="facture-nom_client">
                  Nom Client
                </Label>
                <AvField id="facture-nom_client" type="text" name="nom_client" />
              </AvGroup>
              <AvGroup>
                <Label id="etatLabel" for="facture-etat">
                  Etat
                </Label>
                <AvInput
                  id="facture-etat"
                  type="select"
                  className="form-control"
                  name="etat"
                  value={(!isNew && factureEntity.etat) || 'ACTIF'}
                >
                  <option value="ACTIF">ACTIF</option>
                  <option value="INACTIF">INACTIF</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/facture" replace color="info">
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
  factureEntity: storeState.facture.entity,
  loading: storeState.facture.loading,
  updating: storeState.facture.updating,
  updateSuccess: storeState.facture.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FactureUpdate);
