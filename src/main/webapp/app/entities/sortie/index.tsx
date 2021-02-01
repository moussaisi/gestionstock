import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Sortie from './sortie';
import SortieDetail from './sortie-detail';
import SortieUpdate from './sortie-update';
import SortieDeleteDialog from './sortie-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SortieUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SortieUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SortieDetail} />
      <ErrorBoundaryRoute path={match.url} component={Sortie} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SortieDeleteDialog} />
  </>
);

export default Routes;
