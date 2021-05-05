import {GunProvider} from '../../gun-provider/shared/gun-provider.model';

export class GunType {
  id: number;
  name: string;
  category: string;
  gunProvider: GunProvider;
}
