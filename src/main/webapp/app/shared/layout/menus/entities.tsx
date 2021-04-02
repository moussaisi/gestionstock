import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <MenuItem icon="asterisk" to="/categorie">
      Categorie
    </MenuItem>
    <MenuItem icon="asterisk" to="/produit">
      Produit
    </MenuItem>
    <MenuItem icon="asterisk" to="/stock">
      Stock
    </MenuItem>
    <MenuItem icon="asterisk" to="/facture">
      Facture
    </MenuItem>
    <MenuItem icon="asterisk" to="/sortie">
      Sortie
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
