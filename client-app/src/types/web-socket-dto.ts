export enum ActionType {
  TOGGLE_DEVICE = 'TOGGLE_DEVICE'
}

export interface WebSocketDto {
  actionType: ActionType
}

export interface ToggleDevice {
  id: number;
  active: boolean;
}
