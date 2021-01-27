import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './stock.reducer';
import { IStock } from 'app/shared/model/stock.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStockDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StockDetail = (props: IStockDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { stockEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Stock [<b>{stockEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="quantite">Quantite</span>
          </dt>
          <dd>{stockEntity.quantite}</dd>
          <dt>
            <span id="date">Date</span>
          </dt>
          <dd>{stockEntity.date ? <TextFormat value={stockEntity.date} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date_der_modif">Date Der Modif</span>
          </dt>
          <dd>
            {stockEntity.date_der_modif ? <TextFormat value={stockEntity.date_der_modif} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>Produit</dt>
          <dd>{stockEntity.produit ? stockEntity.produit.libelle_produit : ''}</dd>
        </dl>
        <Button tag={Link} to="/stock" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/stock/${stockEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ stock }: IRootState) => ({
  stockEntity: stock.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StockDetail);
