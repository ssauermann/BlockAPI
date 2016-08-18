package com.tree_bit.rcdl.blocks;

import com.tree_bit.blockapi.id.minecraft.BlockID;
import com.tree_bit.rcdl.blocks.dv.OrientationNESW;


@SuppressWarnings("javadoc")
public class Test {

    public static void main(final String[] args) {
        final Block diamond = Block.getInstance(BlockID.DIAMOND_BLOCK);
        final Block repeater = Block.getInstance(BlockID.REPEATER_OFF, Repeater.getInstance(OrientationNESW.South, Repeater.Delay.D3));

        System.out.println(diamond);
        System.out.println(repeater);

        Block torch = Block.getInstance(BlockID.TORCH);

        System.out.println(torch);

        try {
            torch = torch.rotate(Axis.Y, 90);
        } catch (final PlacementInvalidException e) {
            e.printStackTrace();
        }

        System.out.println(torch);

        // torch.setData(GenericBlockData.getInstance());


        // final HangingSign h = HangingSign.getInstance(SignOrientation.North,
        // new SignEntity(new FormatText[] {FormatText.builder().append(new
        // FormatString("Hello World")).build()}));

        // final HangingSign h2 = HangingSign.getInstance(SignOrientation.North,
        // new SignEntity(new String[] {"Hello World"}));

        // final Set<HangingSign> hs = HangingSign.getInstances();

        // System.out.println(Arrays.toString(HangingSign.getInstances().toArray()));

        // final Block sign = Block.getInstance(BlockID.WALL_SIGN, h);

        // final Block sign2 = Block.getInstance(BlockID.WALL_SIGN, h2);

        // System.out.println(sign == sign2);
    }
}
