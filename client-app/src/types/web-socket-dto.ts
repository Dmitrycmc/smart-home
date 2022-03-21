export enum ActionType {
  TOGGLE_DEVICE = 'TOGGLE_DEVICE'
}

export interface WebSocketDto {
  actionType: ActionType
}

export interface ToggleDevice extends WebSocketDto {
  id: number;
  active: boolean;
}
