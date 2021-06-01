import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Categorie from './categorie';
import Produit from './produit';
import Stock from './stock';
import Facture from './facture';
import Sortie from './sortie';
import Utilisateur from './utilisateur';
import Info from './info';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}categorie`} component={Categorie} />
      <ErrorBoundaryRoute path={`${match.url}produit`} component={Produit} />
      <ErrorBoundaryRoute path={`${match.url}stock`} component={Stock} />
      <ErrorBoundaryRoute path={`${match.url}facture`} component={Facture} />
      <ErrorBoundaryRoute path={`${match.url}sortie`} component={Sortie} />
      <ErrorBoundaryRoute path={`${match.url}utilisateur`} component={Utilisateur} />
      <ErrorBoundaryRoute path={`${match.url}info`} component={Info} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
