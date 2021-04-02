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
      <Col md="9">
        <h2>Bienvenue</h2>
        <p className="lead">Ceci est votre page d'accueil</p>
        {account && account.login ? (
          <div>
            <Alert color="success">Vous etes connecté en tant que {account.login}.</Alert>
          </div>
        ) : (
          <div>
            <Alert color="warning">
              Si vous voulez vous
              <Link to="/login" className="alert-link">
                {' '}
                connecter
              </Link>
              , vous pouvez utiliser les comptes par défauts:
              <br />- Administrateur (Identifiant=&quot;admin&quot; et mot de passe=&quot;admin&quot;)
              <br />- Utilisateur (Identifiant=&quot;user&quot; et mot de passe=&quot;user&quot;).
            </Alert>

            <Alert color="warning">
              Vous n'avez pas encore de compte?&nbsp;
              <Link to="/account/register" className="alert-link">
                Créer une compte
              </Link>
            </Alert>
          </div>
        )}
        <p>Si vous avez des questions concernant JHipster</p>

        <ul>
          <li>
            <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">
              JHipster Accueil
            </a>
          </li>
          <li>
            <a href="http://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">
              JHipster sur Stack Overflow
            </a>
          </li>
          <li>
            <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer">
              JHipster bug tracker
            </a>
          </li>
          <li>
            <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
              Chat Room de JHipster
            </a>
          </li>
          <li>
            <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">
              Suivez @jhipster sur Twitter
            </a>
          </li>
        </ul>

       
      </Col>
      <Col md="3" className="pad">
        <span className="hipster rounded" />
      </Col>
    </Row>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
