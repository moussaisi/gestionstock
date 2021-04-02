import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './facture.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFactureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FactureDetail = (props: IFactureDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { factureEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Facture [<b>{factureEntity.id}</b>]
        </h2>

        <dl className="jh-entity-details">
          <dt>
            <span id="date">Date</span>
          </dt>
          <dd>{factureEntity.date ? <TextFormat value={factureEntity.date} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="quantite">Quantite</span>
          </dt>
          <dd>{factureEntity.quantite}</dd>
          <dt>
            <span id="prenom_client">Prenom Client</span>
          </dt>
          <dd>{factureEntity.prenom_client}</dd>
          <dt>
            <span id="nom_client">Nom Client</span>
          </dt>
          <dd>{factureEntity.nom_client}</dd>
          <dt>
            <span id="etat">Etat</span>
          </dt>
          <dd>{factureEntity.etat}</dd>
        </dl>
        <Button tag={Link} to="/facture" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/facture/${factureEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Imprimer</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ facture }: IRootState) => ({
  factureEntity: facture.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FactureDetail);
