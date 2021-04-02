import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const { account } = props;

  return (
    <Row>
      <Col md="12">
        <h2 className="text-primary">BIENVENUE DANS VOTRE GESTIONNAIRE DE STOCK</h2>
        {account && account.login ? (
          <div>
            <Alert color="success">Bonjour {account.login}.</Alert>
          </div>
        ) : (
          <div>
            <img src="content/images/stock.jpg" width="100%" height="450"/>
          </div>
        )}
      </col>
    </Row>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
