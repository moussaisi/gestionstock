import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sortie.reducer';
import { ISortie } from 'app/shared/model/sortie.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISortieDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SortieDetail = (props: ISortieDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { sortieEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Sortie [<b>{sortieEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="date">Date</span>
          </dt>
          <dd>{sortieEntity.date ? <TextFormat value={sortieEntity.date} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="quantite">Quantite</span>
          </dt>
          <dd>{sortieEntity.quantite}</dd>
          <dt>Produit</dt>
          <dd>{sortieEntity.produit ? sortieEntity.produit.libelle_produit : ''}</dd>
          <dt>Facture</dt>
          <dd>{sortieEntity.facture ? sortieEntity.facture.prenom_client : ''}</dd>
        </dl>
        <Button tag={Link} to="/sortie" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sortie/${sortieEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ sortie }: IRootState) => ({
  sortieEntity: sortie.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SortieDetail);
